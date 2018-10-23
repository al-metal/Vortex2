package com.vortex.vortex;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class CallMeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText phone;
    EditText etQuestion;
    Button btnCalMe;
    Spinner spinner;
    String department;
    String filial;
    String[] data = {"АПК", "Пищепром", "Автохимия", "Клининг", "Общие вопросы"};
    final String LOG_TAG = "myLogs";
    private boolean ERR_WEB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getText(R.string.feedback));
        spinner = (Spinner) findViewById(R.id.spinnerCallMe);

        phone = (EditText) findViewById(R.id.etPhone);
        etQuestion = (EditText) findViewById(R.id.etQuestion);

        MaskImpl mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER);
        mask.setHideHardcodedHead(false); // default value
        FormatWatcher formatWatcher = new MaskFormatWatcher(mask);
        formatWatcher.installOn(phone);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, data);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setPrompt("Выберите направление");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                switch (pos) {
                    case 0:
                        department = "АПК";
                        break;
                    case 1:
                        department = "Пищепром";
                        break;
                    case 2:
                        department = "Автохимия";
                        break;
                    case 3:
                        department = "Клининг";
                        break;
                    case 4:
                        department = "Общие вопросы";
                        break;
                }
            }
        });

        filial = "Центр";

        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroupFilial);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        filial = "err";
                        break;
                    case R.id.rButtonCentralOffice:
                        filial = "Центр";
                        break;
                    case R.id.rButtonSouthOffice:
                        filial = "Юг";
                        break;
                    case R.id.rButtonSiberianOffice:
                        filial = "Сибирь";
                        break;
                    default:
                        filial = "err";
                        break;
                }
            }
        });
    }

    public void onClickIcon(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = ClickLeftMenu.getIntent(CallMeActivity.this, item);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        btnCalMe = (Button) findViewById(R.id.btnCallMe);

        int phoneLength = phone.getText().length();

        if (phoneLength == 18 && !filial.equals("err")) {
            try {
                new SendData().execute();
            } catch (Exception e) {

            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Проверьте ввод номера телефона и выбранный филиал", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    class SendData extends AsyncTask<Void, Void, Void> {

        String resultString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String myURL = "https://pk-vortex.ru/mobail-files/send.php";

                String parammetrs = "phone=" + phone.getText() + "†" + department + "†" + etQuestion.getText() + "†" + filial;//+fio_in+"&dol="+dol_in+"&tel="+tel_in;
                byte[] data = null;
                InputStream is = null;
                ERR_WEB = false;
                Log.d(LOG_TAG, "--- номер " + phone.getText() + " ----");

                try {
                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", "" + Integer.toString(parammetrs.getBytes().length));
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    // конвертируем передаваемую строку в UTF-8
                    data = parammetrs.getBytes("UTF-8");

                    OutputStream os = conn.getOutputStream();

                    // передаем данные на сервер
                    os.write(data);
                    os.flush();
                    os.close();
                    data = null;
                    conn.connect();
                    int responseCode = conn.getResponseCode();


                    // передаем ответ сервер
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    if (responseCode == 200) {    // Если все ОК (ответ 200)
                        is = conn.getInputStream();

                        byte[] buffer = new byte[8192]; // размер буфера

                        // Далее так читаем ответ
                        int bytesRead;

                        while ((bytesRead = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        data = baos.toByteArray();
                        resultString = new String(data, "UTF-8");  // сохраняем в переменную ответ сервера, у нас "OK"

                    } else {
                    }
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    //resultString = "MalformedURLException:" + e.getMessage();
                    Log.d(LOG_TAG, "--- ошибка MalformedURLException" + e + " ----");
                } catch (IOException e) {
                    //resultString = "IOException:" + e.getMessage();
                    Log.d(LOG_TAG, "--- ошибка IOException" + e + " ----");
                    ERR_WEB = true;
                } catch (Exception e) {
                    //resultString = "Exception:" + e.getMessage();
                    Log.d(LOG_TAG, "--- ошибка Exception" + e + " ----");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast toast;
            if (ERR_WEB) {
                toast = Toast.makeText(getApplicationContext(), "К сожалению возникли проблемы с интернетом. Попробуйте отправить заявку позже.", Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(getApplicationContext(), "Данные переданы!", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }
}

