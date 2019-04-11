package com.example.android.michael_1202160197_si4004_pab_modul2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    Switch simpleSwitch;
    TextView getText_waktu_pp, getText_tanggal_pp, getBesar_saldo, getText_tanggal, getText_waktu,
    get_jumlah_tiket;
    Spinner getSpinner;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    final Context context = this;
    int saldo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] arraySpinner = new String[] {
                "Jakarta (Rp 85.000)", "Cirebon (Rp 150.000)", "Bekasi (Rp 70.000)"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner_tiket);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        simpleSwitch = (Switch) findViewById(R.id.switch_pp);
        getText_tanggal = (TextView) findViewById(R.id.text_pilih_tanggal);
        getText_waktu = (TextView) findViewById(R.id.text_pilih_waktu);
        getText_tanggal_pp = (TextView) findViewById(R.id.text_tanggal_pp);
        getText_waktu_pp = (TextView) findViewById(R.id.text_waktu_pp);
        getSpinner = (Spinner) findViewById(R.id.spinner_tiket);
        getBesar_saldo = (TextView) findViewById(R.id.text_harga_saldo);
        get_jumlah_tiket = (EditText) findViewById(R.id.jumlah_tiket);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        getText_tanggal_pp.setVisibility(View.GONE);
        getText_waktu_pp.setVisibility(View.GONE);
    }

//   *** ONCLICK FUNTIONS ***

    public void onClickSwitch(View view) {
        if (simpleSwitch.isChecked()) {
            getText_tanggal_pp.setVisibility(View.VISIBLE);
            getText_waktu_pp.setVisibility(View.VISIBLE);
        }
        else {
            getText_tanggal_pp.setVisibility(View.GONE);
            getText_waktu_pp.setVisibility(View.GONE);
        }
    }
    ////////////////////////
    public void onClickSubmit(View view) {
        String getSpinnerString = getSpinner.getSelectedItem().toString();
        String sTiket = get_jumlah_tiket.getText().toString();
        String sTanggal = getText_tanggal.getText().toString();
        String sTanggal_pp = getText_tanggal_pp.getText().toString();
        String sWaktu = getText_waktu.getText().toString();
        String sWaktu_pp = getText_waktu_pp.getText().toString();
        int harga = 0;
        String tujuan ="";
        String berangkat = sTanggal + " - " + sWaktu;
        String pulang = sTanggal_pp + " - " + sWaktu_pp;
        boolean valid = true;

        if (getSpinnerString.equals("Jakarta (Rp 85.000)")) {
            harga = 85000;
            tujuan = "JAKARTA";
        } else if (getSpinnerString.equals("Cirebon (Rp 150.000)")) {
            harga = 150000;
            tujuan = "CIREBON";
        } else if (getSpinnerString.equals("Bekasi (Rp 70.000)")) {
            harga = 70000;
            tujuan = "BEKASI";
        }

        if (simpleSwitch.isChecked()) {
            harga *= 2;
        }

        if (sTanggal.equals("Pilih Tanggal") && valid == true) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tolong pilih tanggal pergi dahulu",
                    Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
        }
        if (sTanggal_pp.equals("Pilih Tanggal") && simpleSwitch.isChecked() && valid == true) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tolong pilih tanggal pulang dahulu",
                    Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
        }

        if (sWaktu.equals("Pilih Waktu") && valid == true) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tolong pilih waktu pergi dahulu",
                    Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
        }

        if (sWaktu_pp.equals("Pilih Waktu") && simpleSwitch.isChecked() && valid == true) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tolong pilih waktu pulang dahulu",
                    Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
        }

        if (valid == true) {
            if (sTiket.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Tolong masukan jumlah tiket!",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else {
                int Itiket = Integer.parseInt(sTiket);
                int harga_total = harga * Itiket;
                int selisih_saldo = saldo - harga_total;
                if (selisih_saldo < 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Maaf, saldo kamu kurang. Top Up saldo dulu!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("tujuan", tujuan);
                    intent.putExtra("berangkat", berangkat);
                    intent.putExtra("pulang", pulang);
                    intent.putExtra("tiket", sTiket);
                    intent.putExtra("harga_total", Integer.toString(harga_total));
                    intent.putExtra("saldo", Integer.toString(saldo));
                    MainActivity.this.startActivity(intent);
                }
            }
        }
    }
    ////////////////////////
    public void showDialog(View view){
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_dialog);
            TextView tambah_saldo = (TextView) dialog.findViewById(R.id.tambah_saldo);
            TextView cancel = (TextView) dialog.findViewById(R.id.cancel);

            tambah_saldo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText besar_topup = (EditText) dialog.findViewById(R.id.besar_topup);
                    saldo += Integer.parseInt(besar_topup.getText().toString());
                    getBesar_saldo.setText(Integer.toString(saldo));
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Top Up berhasil!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    dialog.dismiss();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

    }
    ////////////////////////
    public void tanggal_onClick(View view){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                getText_tanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    ////////////////////////
    public void tanggal_pp_onClick(View view){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                getText_tanggal_pp.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    ////////////////////////
    public void waktu_onClick(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                getText_waktu.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    ////////////////////////
    public void waktu_pp_onClick(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                getText_waktu_pp.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ini onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "ini onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ini onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ini onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ini onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ini onDestroy");
    }
}
