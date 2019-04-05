package utils;

import interfaces.IClassificationObject;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<IClassificationObject> objects = new ArrayList<>();
    private String label;

    public Group(final String label) {
        this.label = label;
    }

    public void addObject(IClassificationObject object) {
        objects.add(object);
    }

    public List<IClassificationObject> getObjects() {
        return objects;
    }

    public String getLabel() {
        return label;
    }
}
