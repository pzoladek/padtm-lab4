package com.example.padtm_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {
    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);


        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById
                (R.id.gatunek);
        gatunek.setAdapter(gatunki);


        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") != null) {
                Animal zwierz = (Animal) extras.getSerializable("element");

                Spinner formGatunek = (Spinner) findViewById(R.id.gatunek);
                EditText kolor = (EditText) findViewById(R.id.editText);
                EditText wielkosc = (EditText) findViewById(R.id.editText2);
                EditText opis = (EditText) findViewById(R.id.editText3);

                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());

                int i;
                switch (zwierz.getGatunek()) {
                    case "Pies": i = 0; break;
                    case "Kot": i = 1; break;
                    case "Rybki": i = 2;break;
                    default: i = -1;
                }
                formGatunek.setSelection(i);

                this.modyfi_id = zwierz.getId();
            }
        }catch(Exception ex) {
            this.modyfi_id=0;
        }

    }

    public void wyslij(View view) {
        EditText kolor = (EditText) findViewById(R.id.editText);
        EditText wielkosc = (EditText) findViewById (R.id.editText2);
        EditText opis = (EditText) findViewById(R.id.editText3);
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);
        System.out.println("OTO ANIMAL" + gatunek.getSelectedItem().toString() + kolor.getText().toString() +
                Float.valueOf(wielkosc.getText().toString()) + opis.getText().toString());
        Animal zwierze = new Animal(
                gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString()
        );
        zwierze.setId(this.modyfi_id);
        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }
}
