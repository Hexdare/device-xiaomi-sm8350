/*
 * Copyright (C) 2020 Paranoid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.torchcontrol;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;
    import android.os.Looper;

    import androidx.preference.Preference;
    import androidx.preference.PreferenceFragment;
    import androidx.preference.SwitchPreference;

    import org.lineageos.settings.R;

    import org.lineageos.settings.utils.TorchUtils;

public class DcDimmingSettingsFragment extends PreferenceFragment implements
        OnPreferenceChangeListener {
    private static final String PREF_TORCH_STRENGTH = "torch_strength";

    private SeekBarPreference mTorchStrengthPerf;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.torchstrength_settings);\

        mTorchStrengthPerf = (SeekBarPreference) findPreference(PREF_TORCH_STRENGTH);

        mTorchStrengthPerf.setOnPreferenceChangeListener(this);
        mTorchStrengthPerf.setValue(TorchUtils.getTorchStrength());
        mTorchStrengthPerf.setSummary(Integer.toString(TorchUtils.getTorchStrength()) + "%");
        mTorchStrengthPerf.setMin(20);
}

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    switch (preference.getKey()) {
            case PREF_TORCH_STRENGTH:
                mTorchStrengthPerf.setSummary(String.valueOf(newValue) + "%");
                TorchUtils.setTorchStrength((int) newValue);
                return true;
            default:
                return false;
    }
}

