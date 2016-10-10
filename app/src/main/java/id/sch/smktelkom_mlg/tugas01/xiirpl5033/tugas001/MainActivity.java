package id.sch.smktelkom_mlg.tugas01.xiirpl5033.tugas001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText etNamaPem, etNama, etTanggal;
    CheckBox cbRP, cbWC, cbRK, cbPL;
    Button bOk;
    TextView tvHasil, tvSurat, tvNama;
    RadioButton rbPR, rbSR, rbRD, rbRP;
    RadioGroup rgJabatan;
    Spinner spProvinsi, spKota;

    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan",
            "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang",
            "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPem = (EditText) findViewById(R.id.editTextNamaPem);
        etNama = (EditText) findViewById(R.id.editTextNama);
        etTanggal = (EditText) findViewById(R.id.editTextTanggal);

        rbRP = (RadioButton) findViewById(R.id.radioButtonRP);
        rbPR = (RadioButton) findViewById(R.id.radioButtonPR);
        rbSR = (RadioButton) findViewById(R.id.radioButtonSR);
        rbRD = (RadioButton) findViewById(R.id.radioButtonRD);
        rgJabatan = (RadioGroup) findViewById(R.id.radioGroupJabatan);

        cbPL = (CheckBox) findViewById(R.id.checkBoxPL);
        cbWC = (CheckBox) findViewById(R.id.checkBoxWC);
        cbRK = (CheckBox) findViewById(R.id.checkBoxRK);
        cbRP = (CheckBox) findViewById(R.id.checkBoxRP);

        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);

        bOk = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvSurat = (TextView) findViewById(R.id.textViewSurat);
        tvNama = (TextView) findViewById(R.id.textViewNama);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[position]));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();

            }
        });
    }

    private void doProcess() {
        if (isValid()) {
            String namaPem = etNamaPem.getText().toString();
            String nama = etNama.getText().toString();
            String tanggal = etTanggal.getText().toString();

            String jabatan = null;

            if (rgJabatan.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgJabatan.getCheckedRadioButtonId());
                jabatan = rb.getText().toString();
            }

            if (rbPR.isChecked()) {
                jabatan = rbPR.getText().toString();
            } else if (rbSR.isChecked()) {
                jabatan = rbSR.getText().toString();
            } else if (rbRD.isChecked()) {
                jabatan = rbRD.getText().toString();
            } else if (rbRP.isChecked()) {
                jabatan = rbRP.getText().toString();
            }

            String penugasan = "\nJenis Penugasan : ";
            int startlen = penugasan.length();
            if (cbRP.isChecked()) penugasan += cbRP.getText() + " . ";
            if (cbWC.isChecked()) penugasan += cbWC.getText() + " . ";
            if (cbPL.isChecked()) penugasan += cbPL.getText() + " . ";
            if (cbRK.isChecked()) penugasan += cbRK.getText() + " . ";

            if (penugasan.length() == startlen) penugasan += "Tidak ada Pilihan";

            if (jabatan == null) {
                tvHasil.setText("Belum Memilih Jabatan!");
            } else if (penugasan == null) {
                tvHasil.setText("Belum Memilih Jenis Penugasan!");
            } else {
                tvHasil.setText("Nama : " + nama + " \nTanggal : " + tanggal +
                        "\nJabatan : " + jabatan + penugasan +
                        "\nTempat : " + spProvinsi.getSelectedItem().toString() +
                        " kota " + spKota.getSelectedItem().toString());
                tvSurat.setText("Surat Tugas Reporter\n\n");
                tvNama.setText("\n\n" + namaPem);
            }

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String namaPem = etNamaPem.getText().toString();
        String nama = etNama.getText().toString();
        String tanggal = etTanggal.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (namaPem.isEmpty()) {
            etNamaPem.setError("Nama belum diisi");
            valid = false;
        } else if (namaPem.length() < 3) {
            etNamaPem.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNamaPem.setError(null);
        }

        if (tanggal.isEmpty()) {
            etTanggal.setError("Tanggal belum diisi");
            valid = false;
        } else {
            etTanggal.setError(null);
        }

        return valid;
    }
}
