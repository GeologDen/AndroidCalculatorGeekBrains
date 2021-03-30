package com.geekbrains.androidcalculatorgeekbrains;

import java.io.Serializable;
import java.util.ArrayList;

public class Actions implements Serializable {
    ArrayList<String> pressedActions = new ArrayList<>();

    public void add(String buttonName) {
        pressedActions.add(buttonName);
    }

    public String resultString() {
        StringBuilder result = new StringBuilder();
        for (String pressedAction : pressedActions) {
            result.append(pressedAction);
        }
        return result.toString();
    }
}
