package com.github.asufana.ddd.vo.validations;

import static org.apache.commons.lang3.StringUtils.*;

import java.lang.reflect.*;
import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.joda.time.*;

import com.github.asufana.ddd.vo.*;
import com.github.asufana.ddd.vo.exceptions.*;

public class ColumnAnnotationValidateFunction {
    
    public static <T extends AbstractValueObject> void validate(final T vo) {
        try {
            fields(vo).validate();
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ValueObjectException(e);
        }
    }
    
    /** (staticを除く）ローカルフィールド一覧 */
    public static <T extends AbstractValueObject> FieldInfoCollection fields(final T vo) {
        final List<FieldInfo> fields = new ArrayList<FieldInfo>();
        for (final Field field : vo.getClass().getDeclaredFields()) {
            //staticフィールドは除外
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            fields.add(new FieldInfo(field, vo));
        }
        return new FieldInfoCollection(fields);
    }
    
    //-----------------------------------------
    // バリデーション用内部クラス
    //-----------------------------------------
    
    /** Fieldラッパークラス */
    public static class FieldInfo {
        //フィールド定義
        public final Field field;
        /** フィールド名 */
        public final String name;
        //実インスタンス
        public final Object object;
        //フィールドの文字長制約（制約設定がなければnull）
        public final Integer length;
        //フィールドがnull許容かどうか
        public final boolean nullable;
        
        //インスタンス
        public FieldInfo(final Field field, final Object object) {
            this.field = field;
            this.object = object;
            name = field.getName();
            length = length(field);
            nullable = nullable(field);
            
            //内部型種別の確認
            isSupported(field);
        }
        
        /** Columnアノテーションのlength値 */
        private Integer length(final Field field) {
            //String以外は文字長チェックしない
            if (field.getType().equals(String.class) == false) {
                return null;
            }
            final Column column = getColumnAnnotation(field);
            return column != null
                    ? column.length()
                    : null;
        }
        
        /** Columnアノテーションのnullable値 */
        private boolean nullable(final Field field) {
            final Column column = getColumnAnnotation(field);
            return column != null
                    ? column.nullable()
                    : true;
        }
        
        private Column getColumnAnnotation(final Field field) {
            return field.getDeclaredAnnotation(Column.class);
        }
        
        /** 内部クラス種別の確認 */
        public void isSupported(final Field field) {
            if (getColumnAnnotation(field) == null) {
                return;
            }
            
            final Class<?> clazz = field.getType();
            if (!clazz.equals(String.class)
                    && !clazz.equals(Integer.class)
                    && !clazz.equals(Long.class)
                    && !clazz.equals(BigDecimal.class)
                    && !clazz.equals(DateTime.class)
                    && !clazz.equals(Boolean.class)) {
                throw new ValueObjectException("指定の型には対応していません："
                        + clazz.getName());
            }
        }
        
        /** バリデーション */
        public void validate() throws IllegalArgumentException, IllegalAccessException {
            if (getColumnAnnotation(field) == null) {
                return;
            }
            validateNotNull();
            validateLength();
        }
        
        //文字長確認
        void validateLength() throws IllegalArgumentException, IllegalAccessException {
            final Object o = field.get(object);
            if (o != null && length != null) {
                final String value = (String) o;
                if (value.length() > length) {
                    throw ValueObjectException.overLengthException(object,
                                                                   field);
                }
            }
        }
        
        //null許容確認
        void validateNotNull() throws IllegalArgumentException, IllegalAccessException {
            final Object o = field.get(object);
            if (nullable == false) {
                if (o == null) {
                    throw ValueObjectException.nullException(object, field);
                }
                //String型の場合、空文字は不可
                if (length != null && isEmpty((String) o)) {
                    throw ValueObjectException.nullException(object, field);
                }
            }
        }
    }
    
    /** Fieldクラスコレクション */
    public static class FieldInfoCollection {
        private List<FieldInfo> fields;
        
        public List<FieldInfo> list() {
            return fields;
        }
        
        //コンストラクタ
        public FieldInfoCollection(final List<FieldInfo> fields) {
            if (fields == null || fields.size() == 0) {
                this.fields = Collections.emptyList();
            }
            this.fields = Collections.unmodifiableList(fields);
        }
        
        /** バリデーション */
        public void validate() throws IllegalArgumentException, IllegalAccessException {
            for (final FieldInfo f : fields) {
                f.validate();
            }
        }
        
        /** フィールドの取得 */
        public FieldInfo get(final String fieldName) {
            for (final FieldInfo f : fields) {
                if (f.name.toLowerCase().equals(fieldName.toLowerCase())) {
                    return f;
                }
            }
            return null;
        }
    }
}
