package com.devs.kero.team7.data.mapper;

import com.devs.kero.team7.data.dataModel.PopupNotification;

import java.text.ParseException;

public class PopupMapper  {

    public static PopupNotification to(String s) {
        if(s.equals("NoPopup")) return  PopupNotification.NoPopup;
       else if(s.equals("NoOnLockScreen")) return PopupNotification.NoOnLockScreen;
        else if (s.equals("Always")) {
            return PopupNotification.Always ;
        }else  return PopupNotification.NoOnLockScreen;

    }


    public  static String from(PopupNotification popupNotification) throws ParseException {
    switch (popupNotification){
        case Always:return "Always";
        case NoPopup: return "NoPopup" ;
        case NoOnLockScreen: return "NoOnLockScreen";
        default: return "NoOnLockScreen";
    }
    }
}
