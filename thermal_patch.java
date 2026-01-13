// Add these imports if missing
import android.os.BatteryManager;
import android.content.Intent;
import android.content.IntentFilter;

// Inside XServerDisplayActivity class, add this method or inject into onResume/onCreate
private void checkThermalThrottling() {
    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent batteryStatus = registerReceiver(null, ifilter);
    if (batteryStatus != null) {
        int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        if (temp > 400) { // 40°C
            Log.d("ThermalThrottling", "Temperature high (" + temp + "), limiting FPS to 30");
            envVars.put("DXVK_FRAME_RATE", "30");
            // Alternatively, if using dxvk.hud:
            // envVars.put("DXVK_HUD", "fps,devinfo"); 
        }
    }
}
