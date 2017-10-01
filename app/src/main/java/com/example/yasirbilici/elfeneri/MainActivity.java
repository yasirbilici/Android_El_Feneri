package com.example.yasirbilici.elfeneri;


import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btnDegisim;
    private Camera camera;
    private boolean flashdurum;
    Camera.Parameters params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDegisim = (ImageButton) findViewById(R.id.imageButton);

        //kameraya erişmeyi saglayan method
        KameraAc();
        //Flash'ın durumuna göre button'un resmini ayarlıyoruz
        buttondegisimi();

    }
    /*button tıklama durumu ayarlandı
    (id'si activity_mai.xml içersinde android:onClick="BtnFlash" satırıyla verildi)
     */
    public void BtnFlash(View arg0){
        //flash'ın acık yada kapalı ola durumları konrol edilip
        //acıksa kapatılması kapalıysa acılması saglandı
        if (flashdurum) {

            flashKapat();
        } else {
            flashAc();
        }
    }
    //flash'ı kullanmak için kameraya erişmemizi saglayan method
    private void KameraAc() {
        if (camera == null) {
            camera = Camera.open();
            params = camera.getParameters();

        }
    }

    //flash'ı açma görevini üslenen method
    private void flashAc() {
        if (!flashdurum) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            flashdurum = true;
            buttondegisimi();
        }

    }

    //acık olan flash'ı kapatmak üzere kullanılan method
    private void flashKapat() {
        if (flashdurum) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            flashdurum = false;
            buttondegisimi();
        }
    }
    //flash kapalı yada cık olma durumuna göre buttonu degistiren method
    private void buttondegisimi(){
        if(flashdurum){
            btnDegisim.setImageResource(R.drawable.flashon);
        }else{
            btnDegisim.setImageResource(R.drawable.flashoff);
        }
    }
}