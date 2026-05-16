package finalProject2026;

public class EntityGroup {
    private Object[] entities;
    private State state;

    public EntityGroup(Object[] entities, State state) {
        this.entities = entities;
        this.state = state;
    }

    public State getState() { return state; }
    public Object[] getEntities() { return entities; }
}
