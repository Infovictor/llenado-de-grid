package com.example.rogeliohernndez.ayudagit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import org.kobjects.xml.XmlReader;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String folio;
    public String nom;
    public String Stot;
    public String Sven;
    public String Seg;
    public String Smin;
    public String NC;
    public TextView Tit;
    String Re,mensaje;
    GridView grivivisitas;
    Button btnRegresar, btnCargar;
    Boolean mensajeParse;
    ProgressDialog pdialog;

    String arreglo [][];

    ArrayList resul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegresar =(Button)findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent llenar_datos =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(llenar_datos);
            }
        });

        Button btnCargar =(Button)findViewById(R.id.btnCargar);
        btnCargar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                llenar_grid sp =new llenar_grid();
                sp.execute();
            }
        });

    }

    class  llenar_grid extends AsyncTask<Void,Void,Void>
    {
        String resultado;

        @Override
        protected Void doInBackground(Void... params) {
            loadDates();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(MainActivity.this);
            pdialog.setMessage("Cargando datos");
            pdialog.setIndeterminate(false);
            pdialog.setMax(10);
            pdialog.setCancelable(false);
            pdialog.show();
        }

        @Override
        protected void onPostExecute(Void result)
        {
            if (Re.compareTo("true")==0){

            }
            else{
                Toast toas =Toast.makeText(getApplicationContext(),"No se encontraron datos",Toast.LENGTH_LONG);
                toas.show();
            }
            Toast.makeText(MainActivity.this,"No se", Toast.LENGTH_LONG).show();
        }
    }

    private String loadDates()
    {
        String mensaje;
        String url="";
        String metodo="";
        String namespace="";
        String Accion="";

        try
        {
            SoapObject obtencion=new SoapObject(namespace,metodo);

            //obtencion.addProperty("nombre", "nombre");
            //obtencion.addProperty("telefono", "telefono");

            Xml respuesta;

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet=true;
            soapEnvelope.setOutputSoapObject(obtencion);

            HttpTransportSE transporte = new HttpTransportSE(url, 7000);
            transporte.call(Accion, soapEnvelope);

            soapEnvelope.setOutputSoapObject(soapEnvelope);
            arreglo= (String[][]) soapEnvelope.getResponse();

            respuesta = (Xml) soapEnvelope.getResponse();

            mensaje=respuesta.toString();
            //Stot=resultado.toString();

        }
        catch (Exception ex)
        {
            mensaje=ex.getMessage();
        }
        return mensaje;
    }
}
