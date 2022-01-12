package com.ali.quizapp.SpinWheel.model;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int secondaryTextOrientation;
    public int icon;
    public int color;
    public int textColor;

    public LuckyItem() {
    }

    public LuckyItem(String topText, String secondaryText, int secondaryTextOrientation, int icon, int color, int textColor) {
        this.topText = topText;
        this.secondaryText = secondaryText;
        this.secondaryTextOrientation = secondaryTextOrientation;
        this.icon = icon;
        this.color = color;
        this.textColor = textColor;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public int getSecondaryTextOrientation() {
        return secondaryTextOrientation;
    }

    public void setSecondaryTextOrientation(int secondaryTextOrientation) {
        this.secondaryTextOrientation = secondaryTextOrientation;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
