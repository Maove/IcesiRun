package apps.icesirun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Preguntas extends Activity {

    public final static String RESULTADO = "apps.icesirun.result";


    private ArrayList<Integer> preguntasHechas1;
    private int preguntasCorrectas, numeroVidas, preguntasRealizadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        Intent intent = getIntent();
        String nivel = intent.getStringExtra(Opciones.NIVEL);
        preguntasCorrectas = 0;
        numeroVidas = 3;
        preguntasHechas1 = new ArrayList<Integer>();
        if (nivel.equals("Nivel1")) {
            leerPreguntas("Nivel1");
        }
    }

    public void leerPreguntas(String nivel) {

        if (nivel.equals("Nivel1")) {
            Random rnd = new Random();
            int numeroPregunta = rnd.nextInt(20 - 1 + 1) + 1;
            System.out.println("Numero Pregunta" + numeroPregunta);

            boolean hecha = false;
            if (preguntasHechas1.contains(numeroPregunta)) {
                hecha = true;
            }
            System.out.println(hecha);

            if (hecha) {
                leerPreguntas("Nivel1");
            } else {
                try {
                    InputStream fraw = getResources().openRawResource(R.raw.listapreguntas);
                    System.out.println(fraw != null);
                    BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
                    int contadorLinea = 0;
                    String linea = "";
                    while (contadorLinea != numeroPregunta) {
                        linea = brin.readLine();
                        contadorLinea++;
                    }
                    fraw.close();
                    preguntasHechas1.add(numeroPregunta);
                    System.out.println("Numero linea " + contadorLinea);
                    System.out.println("La linea es " + linea);
                    String[] infoPregunta = linea.split(",");
                    String textoPregunta = infoPregunta[0];
                    String textoOpcion1 = infoPregunta[1];
                    String textoOpcion2 = infoPregunta[2];
                    String textoOpcion3 = infoPregunta[3];
                    String textoOpcion4 = infoPregunta[4];
                    TextView tvPregunta = (TextView) findViewById(R.id.textoPregunta);
                    tvPregunta.setText(textoPregunta);
                    final String textoOpcionCorrecta = infoPregunta[5];
                    System.out.println("Opcion correcta es " + textoOpcionCorrecta);
                    final Button btOpcion1 = (Button) findViewById(R.id.botonOpcion1);
                    btOpcion1.setText(textoOpcion1);
                    btOpcion1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seleccionarRespuesta(btOpcion1,textoOpcionCorrecta);
                        }
                    });
                    final Button btOpcion2 = (Button) findViewById(R.id.botonOpcion2);
                    btOpcion2.setText(textoOpcion2);
                    btOpcion2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seleccionarRespuesta(btOpcion2, textoOpcionCorrecta);
                        }
                    });
                    final Button btOpcion3 = (Button) findViewById(R.id.botonOpcion3);
                    btOpcion3.setText(textoOpcion3);
                    btOpcion3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seleccionarRespuesta(btOpcion3, textoOpcionCorrecta);
                        }
                    });
                    final Button btOpcion4 = (Button) findViewById(R.id.botonOpcion4);
                    btOpcion4.setText(textoOpcion4);
                    btOpcion4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seleccionarRespuesta(btOpcion4, textoOpcionCorrecta);
                        }
                    });
                } catch (Exception ex) {
                    new AlertDialog.Builder(Preguntas.this).setTitle("Error de ejecución")
                            .setMessage("Ha ocurrido un error al buscar la pregunta").show();
                }
            }
        }
    }

    public void seleccionarRespuesta(Button arg0, String opcionCorrecta) {
        switch (arg0.getId()) {
            case R.id.botonOpcion1:
                Button btOpcion1 = (Button) findViewById(R.id.botonOpcion1);
                if (opcionCorrecta.equals(btOpcion1.getText())) {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Correcta");
                    preguntasCorrectas++;
                    TextView preguntasCorrec = (TextView) findViewById(R.id.numeroPreguntasCorrectas);
                    preguntasCorrec.setText("" + preguntasCorrectas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    leerPreguntas("Nivel1");
                } else {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Incorrecta");
                    numeroVidas--;
                    TextView vidas = (TextView) findViewById(R.id.vidas);
                    vidas.setText("" + numeroVidas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (numeroVidas == 0) {
                        correcta.setText("Has perdido todas las vidas");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent ultimo = new Intent(this,Final.class);
                        ultimo.putExtra(RESULTADO, "Has perdido todas las vidas");
                        startActivity(ultimo);
                    } else {
                        leerPreguntas("Nivel1");
                    }
                }
                break;
            case R.id.botonOpcion2:
                Button btOpcion2 = (Button) findViewById(R.id.botonOpcion2);
                if (opcionCorrecta.equals(btOpcion2.getText())) {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Correcta");
                    preguntasCorrectas++;
                    TextView preguntasCorrec = (TextView) findViewById(R.id.numeroPreguntasCorrectas);
                    preguntasCorrec.setText("" + preguntasCorrectas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    leerPreguntas("Nivel1");
                } else {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Incorrecta");
                    numeroVidas--;
                    TextView vidas = (TextView) findViewById(R.id.vidas);
                    vidas.setText("" + numeroVidas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (numeroVidas == 0) {
                        correcta.setText("Has perdido todas las vidas");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent ultimo = new Intent(this,Final.class);
                        ultimo.putExtra(RESULTADO, "Has perdido todas las vidas");
                        startActivity(ultimo);
                    } else {
                        leerPreguntas("Nivel1");
                    }
                }
                break;
            case R.id.botonOpcion3:
                Button btOpcion3 = (Button) findViewById(R.id.botonOpcion3);
                if (opcionCorrecta.equals(btOpcion3.getText())) {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Correcta");
                    preguntasCorrectas++;
                    TextView preguntasCorrec = (TextView) findViewById(R.id.numeroPreguntasCorrectas);
                    preguntasCorrec.setText("" + preguntasCorrectas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    leerPreguntas("Nivel1");
                } else {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Incorrecta");
                    numeroVidas--;
                    TextView vidas = (TextView) findViewById(R.id.vidas);
                    vidas.setText("" + numeroVidas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (numeroVidas == 0) {
                        correcta.setText("Has perdido todas las vidas");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent ultimo = new Intent(this,Final.class);
                        ultimo.putExtra(RESULTADO, "Has perdido todas las vidas");
                        startActivity(ultimo);
                    } else {
                        leerPreguntas("Nivel1");
                    }
                }
                break;
            case R.id.botonOpcion4:
                Button btOpcion4 = (Button) findViewById(R.id.botonOpcion4);
                if (opcionCorrecta.equals(btOpcion4.getText())) {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Correcta");
                    preguntasCorrectas++;
                    TextView preguntasCorrec = (TextView) findViewById(R.id.numeroPreguntasCorrectas);
                    preguntasCorrec.setText("" + preguntasCorrectas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    leerPreguntas("Nivel1");
                } else {
                    TextView correcta = (TextView) findViewById(R.id.resultado);
                    correcta.setText("Incorrecta");
                    numeroVidas--;
                    TextView vidas = (TextView) findViewById(R.id.vidas);
                    vidas.setText("" + numeroVidas);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (numeroVidas == 0) {
                        correcta.setText("Has perdido todas las vidas");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent ultimo = new Intent(this,Final.class);
                        ultimo.putExtra(RESULTADO, "Has perdido todas las vidas");
                        startActivity(ultimo);
                    } else {
                        leerPreguntas("Nivel1");
                    }
                }
                break;
        }

        preguntasRealizadas++;
        if (preguntasCorrectas == 5) {
            TextView correcta = (TextView) findViewById(R.id.resultado);
            correcta.setText("Has ganado!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent ultimo = new Intent(this,Final.class);
            ultimo.putExtra(RESULTADO, "Has ganado!");
            startActivity(ultimo);
        }

        System.out.println("Preguntas correctas " + preguntasCorrectas);
        System.out.println("Preguntas realizadas " + preguntasRealizadas);
        System.out.println("Vidas " + numeroVidas);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
