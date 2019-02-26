package com.angler.lc3.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat

class MGGpsLocation : LocationListener {

    private var mContext: Context? = null

    // flag for GPS status
    private var isGPSEnabled = false

    // flag for network status
    private var isNetworkEnabled = false

    // flag for GPS status
    private var canGetLocation = false

    private var location: Location? = null // location
    private var latitude: Double = 0.toDouble() // latitude
    private var longitude: Double = 0.toDouble() // longitude
    private var myLatLng: LatLng? = null

    // Declaring a Location Manager
    protected var locationManager: LocationManager? = null

    /**
     * Function to get latitude and longitude
     */
    // return latitude and longitude
    val latLng: LatLng
        get() {
            if (location != null) {
                myLatLng = LatLng(
                    location!!.latitude,
                    location!!.longitude
                )

            }
            return this.myLatLng!!
        }

    /**
     * Function to check whether the gps is enable or not
     */
    // getting GPS status
    // return GpsStatus
    val gpsStatus: Boolean
        get() {
            isGPSEnabled = locationManager!!
                .isProviderEnabled(LocationManager.GPS_PROVIDER)
            return isGPSEnabled
        }

    /**
     * Function to check whether the network is enable or not
     */
    // getting network status
    // return NetworkStatus
    val networkStatus: Boolean
        get() {
            isNetworkEnabled = locationManager!!
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            return isNetworkEnabled
        }

    constructor(context: FragmentActivity) {
        this.mContext = context
        getLocation()
    }

    constructor(context: Activity) {
        this.mContext = context
        getLocation()
    }

    fun getLocation(): Location? {
        try {
            locationManager = mContext!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                showSettingsAlert()
            } else {
                this.canGetLocation = true
                try {
                    if (isNetworkEnabled) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(),
                            this
                        )
                        Log.d("Network", "Network Enabled")
                        if (locationManager != null) {
                            location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    try {
                        if (location == null) {
                            if (ActivityCompat.checkSelfPermission(
                                    mContext!!,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                                    mContext!!,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                // location permissions have not been granted.
                                if (ActivityCompat.shouldShowRequestPermissionRationale(
                                        (mContext as Activity?)!!,
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                    )
                                ) {

                                    // Provide an additional rationale to the user if the permission was not granted
                                    // and the user would benefit from additional context for the use of the permission.
                                    // For example, if the request has been denied previously.

                                    // Display a SnackBar with an explanation and a button to trigger the request.

                                    val alertDialog = AlertDialog.Builder(mContext)

                                    // Setting Dialog Title
                                    //  alertDialog.setTitle("GPS setting");

                                    // Setting Dialog Message
                                    alertDialog.setMessage("Allow to access to your location")

                                    // On pressing Settings button
                                    alertDialog.setPositiveButton(
                                        "Yes"
                                    ) { dialog, which ->
                                        ActivityCompat.requestPermissions(
                                            (mContext as Activity?)!!, PERMISSIONS_LOCATION,
                                            0
                                        )
                                    }

                                    // on pressing cancel button
                                    alertDialog.setNegativeButton(
                                        "No"
                                    ) { dialog, which -> dialog.cancel() }

                                    // Showing Alert Message
                                    alertDialog.show()

                                } else {
                                    // Contact permissions have not been granted yet. Request them directly.
                                    ActivityCompat.requestPermissions(
                                        (mContext as Activity?)!!,
                                        PERMISSIONS_LOCATION,
                                        0
                                    )
                                }

                            }

                            locationManager!!.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                0,
                                0f, this
                            )

                            locationManager!!.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                            )
                            Log.d("GPS", "GPS Enabled")
                            if (locationManager != null) {
                                location = locationManager!!
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                                if (location != null) {
                                    latitude = location!!.latitude
                                    longitude = location!!.longitude
                                }
                            }
                        }

                    } catch (e: SecurityException) {
                        e.printStackTrace()
                    }

                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     */
    fun stopUsingGPS() {
        try {
            if (locationManager != null) {
                locationManager!!.removeUpdates(this@MGGpsLocation)
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

    }

    /**
     * Function to get latitude
     */
    fun getLatitude(): Double {
        if (location != null) {
            latitude = location!!.latitude
        }

        // return latitude
        return latitude
    }

    /**
     * Function to get longitude
     */
    fun getLongitude(): Double {
        if (location != null) {
            longitude = location!!.longitude
        }

        // return longitude
        return longitude
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return this.canGetLocation
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        // Setting Dialog Title
        alertDialog.setTitle("GPS setting")

        // Setting Dialog Message
        alertDialog
            .setMessage("GPS is not enabled. Do you wish to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext!!.startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton("No") { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    fun showSettingsOneAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        // Setting Dialog Title
        alertDialog.setTitle("GPS setting")

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you wish to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext!!.startActivity(intent)
        }


        // Showing Alert Message
        alertDialog.show()
    }

    override fun onLocationChanged(alocation: Location?) {
        if (alocation != null) {
            location = alocation
        }
    }

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

    companion object {

        // The minimum distance to change Updates in meters
        private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 0 // 100 meters

        // The minimum time between updates in milliseconds
        private val MIN_TIME_BW_UPDATES = (5 * 1000).toLong() // 30 minute

        private val PERMISSIONS_LOCATION =
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        /**
         * Get distance between two locations
         *
         * @param aFromLat
         * @param aFromLongitude
         * @param aToLat
         * @param aToLongitude
         * @return Distance in KM
         */
        fun getDistanceInKM(
            aFromLat: String,
            aFromLongitude: String, aToLat: String, aToLongitude: String
        ): String {

            var aDistance = "0"
            try {
                val aLocation1 = Location("locationA")
                aLocation1.latitude = java.lang.Double.parseDouble(aFromLat)
                aLocation1.longitude = java.lang.Double.parseDouble(aFromLongitude)
                val aLocation2 = Location("locationB")
                aLocation2.latitude = java.lang.Double.parseDouble(aToLat)
                aLocation2.longitude = java.lang.Double.parseDouble(aToLongitude)

                aDistance = DecimalFormat("##.##").format(
                    (aLocation1
                        .distanceTo(aLocation2) / 1000).toDouble()
                )
            } catch (e: Exception) {

                return aDistance
            }

            return aDistance

        }
    }

    /*   public static double sumDistance(ArrayList<Marker> mMapList) {
        Location loc = new Location("distance provider");
        double previousLatitude = mMapList.get(0).getPosition().latitude;
        double previousLongitude = mMapList.get(0).getPosition().longitude;
        float[] results = {0};
        for (int i = 1; i < mMapList.size(); i++) {
            loc.distanceBetween(previousLatitude, previousLongitude, mMapList.get(i).getPosition().latitude,
                    mMapList.get(i).getPosition().longitude, results);
            previousLatitude = mMapList.get(i).getPosition().latitude;
            previousLongitude = mMapList.get(i).getPosition().longitude;
        }

        Log.e("Sum", "" + results[0]);
        return results[0];
    }*/


}
