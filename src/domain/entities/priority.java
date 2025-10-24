package domain.entities;

public enum priority {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий");

    private String displayStatement;
    priority(String displayStatement){
        this.displayStatement = displayStatement;
    }

    public String getDisplayStatement() {
        return displayStatement;
    }

    public void setDisplayStatement(String displayStatement){
        this.displayStatement = displayStatement;
    }
}
