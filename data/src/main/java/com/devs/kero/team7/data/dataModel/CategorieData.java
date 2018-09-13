package com.devs.kero.team7.data.dataModel;


import io.reactivex.annotations.NonNull;

public class CategorieData {
    @NonNull
    private int Id ;

    @NonNull
    private String SoundPath ;
    @NonNull
    private boolean Vibration ;
    @NonNull
    private PopupNotification popupNotification;
    @NonNull
    private boolean TurnOnScreen ;
    @NonNull
    private String NotificationTitle ;

    public CategorieData() {
    }

    public CategorieData(int id, String soundPath, boolean vibration, PopupNotification popupNotification, Boolean turnOnScreen, String notificationTitle) {
        Id = id;
        SoundPath = soundPath;
        Vibration = vibration;
        this.popupNotification = popupNotification;
        TurnOnScreen = turnOnScreen;
        NotificationTitle = notificationTitle;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSoundPath() {
        return SoundPath;
    }

    public void setSoundPath(String soundPath) {
        SoundPath = soundPath;
    }

    public boolean getVibration() {
        return Vibration;
    }

    public void setVibration(boolean vibration) {
        Vibration = vibration;
    }

    public PopupNotification getPopupNotification() {
        return popupNotification;
    }

    public void setPopupNotification(PopupNotification popupNotification) {
        this.popupNotification = popupNotification;
    }

    public boolean getTurnOnScreen() {
        return TurnOnScreen;
    }

    public void setTurnOnScreen(boolean turnOnScreen) {
        TurnOnScreen = turnOnScreen;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }
}
