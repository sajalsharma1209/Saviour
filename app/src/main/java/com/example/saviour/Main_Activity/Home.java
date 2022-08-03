package com.example.saviour.Main_Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.saviour.Conn;
import com.example.saviour.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest mLocationRequest;
    boolean resumeToGetLocation = true;

    double lat, log;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                lat = location.getLatitude();
                log = location.getLongitude();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if (resumeToGetLocation) {
            startLocationUpdates();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CardView clicksasbutton;
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        Dexter.withContext(getContext())
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            displayLocationSettingsRequest(v.getContext());
        }


        clicksasbutton = v.findViewById(R.id.sosclickbutton);
        clicksasbutton.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    send_sms();
                    //Toast.makeText(getContext(), "Submit", Toast.LENGTH_SHORT).show();
                } else {
                    displayLocationSettingsRequest(getContext());
                    //Toast.makeText(getContext(), "Android", Toast.LENGTH_SHORT).show();
                }

            } else {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE}, 0);
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        mLocationRequest = LocationRequest.create()
                .setPriority(100)
                .setInterval(5)
                .setFastestInterval(0);


        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        resumeToGetLocation = true;
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void send_sms() {


        String message = getMessage();
        SmsManager smsManager = SmsManager.getDefault();
        Conn conn = new Conn(getContext());
        Cursor cursor = conn.get_members();

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                lat = location.getLatitude();
                log = location.getLongitude();
            } else {
                startLocationUpdates();
                resumeToGetLocation = false;

            }


            String string3 = "http://maps.google.com/?q=" +
                    (lat) +
                    "," +
                    (log);

            String string2 = message +
                    "\n\nPlease reach ASAP to the given location below\n\n" +
                    string3;
            ArrayList<String> arrayList = smsManager.divideMessage(string2);
            while (cursor.moveToNext()) {
                smsManager.sendMultipartTextMessage(cursor.getString(2), null, arrayList, null, null);
                Toast.makeText(Home.this.getContext(), "SOS Message sent to " + cursor.getString(1), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getMessage() {
        Conn conn = new Conn(getContext());
        String message = null;
        Cursor cursor = conn.get_message();
        if (cursor.moveToFirst())
            message = cursor.getString(1);
        return message;
    }

    private void displayLocationSettingsRequest(Context context) {


        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        settingsBuilder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(requireContext())
                .checkLocationSettings(settingsBuilder.build());

        result.addOnCompleteListener(task -> {
            try {
                LocationSettingsResponse response =
                        task.getResult(ApiException.class);
            } catch (ApiException ex) {
                switch (ex.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvableApiException =
                                    (ResolvableApiException) ex;
                            resolvableApiException
                                    .startResolutionForResult((Activity) requireContext(), 101);

                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }
}