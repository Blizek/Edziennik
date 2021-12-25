package features;

import com.jfoenix.controls.JFXToggleButton;

public class SetCheckingToggleButtonStatus {
    public static void setOn(JFXToggleButton status) {
        status.setSelected(true);
        status.setText("ON");
    }

    public static void setOff(JFXToggleButton status) {
        status.setSelected(false);
        status.setText("OFF");
    }
}
