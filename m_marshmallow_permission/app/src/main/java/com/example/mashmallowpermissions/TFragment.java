package com.example.mashmallowpermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peng on 8/22/16.
 */
public class TFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            Log.d("TFragment", "asfasdg");
        }

        boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE);

        requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 100);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
