package Servlet;

public enum TipAkcije {

    LIST("list"), ADD("add"), EDIT("edit"), DELETE("delete");

    private String action;

    private TipAkcije(String action) {
        this.action = action;
    }

    public static TipAkcije getForAction(String action) {
        for (TipAkcije tipAkcije : values()) {
            if (tipAkcije.action.equals(action)) {
                return tipAkcije;
            }
        }
        return LIST;
    }

}
