package play.db.jpa;

public class GenericModel {
    
    //PlayFramework1 dummy class
    
    public <T extends JPABase> T save() {
        return null;
    }
    
    public static class JPABase {}
}
