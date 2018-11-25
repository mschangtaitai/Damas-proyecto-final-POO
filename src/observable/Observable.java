package observable;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers;
    private boolean isChanged = false;

    public boolean hasChanged() {
        return isChanged;
    }

    public void setChanged() {
        isChanged = true;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String arg) {
        if (hasChanged()) {
            for (int i = 0; i < observers.size(); i++){
                observers.get(i).update(this, arg);
            };
        }
        clearChanged();
    }

    public void clearChanged() {
        isChanged = false;
    }
}
