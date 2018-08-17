package com.example.tejas.hack_iiid.MealRegister;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.hack_iiid.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabel;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MealRegister extends AppCompatActivity {

    FirebaseVisionCloudDetectorOptions mDetectorOptions;
    Bitmap mealImageBitmap=null;
    public static final int MEAL_IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_register);
        setUp();
    }

    public void initializeDetectorOptions() {
        mDetectorOptions = new FirebaseVisionCloudDetectorOptions.Builder()
                .setMaxResults(5)
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .build();
    }


    public void setUp() {
        FirebaseApp.initializeApp(MealRegister.this);
        renderUserInfo();
        imageCapturer();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MEAL_IMAGE_REQUEST){
            if(data!=null) {
                if(data.getExtras().get("data")!=null) {
                    mealImageBitmap = (Bitmap) data.getExtras().get("data");
                    renderImageInView();
                    initializeDetectorOptions();
                    runModel(mealImageBitmap);
                }
            }
        }
    }

    private void renderImageInView() {
        findViewById(R.id.meal_pic_layout).setVisibility(View.VISIBLE);
        ImageView mealImageView=findViewById(R.id.meal_image_view);
        mealImageView.setImageBitmap(mealImageBitmap);
    }

    private void openCamera(){
        Dexter.withActivity(MealRegister.this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d("bug","clicked 1");
                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,MEAL_IMAGE_REQUEST);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Log.d("bug","clicked 2");
                        DialogOnDeniedPermissionListener.Builder
                                .withContext(getApplicationContext())
                                .withTitle("Permission Denied")
                                .withMessage("Camera Service is Necessary")
                                .build();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        Log.d("bug","clicked 3");
                        token.continuePermissionRequest();
                    }
                });
    }
    private void imageCapturer() {

        Button uploadImage=findViewById(R.id.upload_meal_pic);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("bug","clicked");
                //openCamera();
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,MEAL_IMAGE_REQUEST);
            }
        });

    }

    private void renderUserInfo() {
        PieChart macrosChart = findViewById(R.id.macro_pie_chart);
        macrosChart.setUsePercentValues(true);
        ArrayList<Entry> macroValues = new ArrayList<Entry>();
        macroValues.add(new Entry(44f, 0));
        macroValues.add(new Entry(26f, 1));
        macroValues.add(new Entry(30f, 2));

        PieDataSet macroValueSet = new PieDataSet(macroValues, "Macros");


        ArrayList<String> macroLabels = new ArrayList<>();
        macroLabels.add("Carbohydrates");
        macroLabels.add("Proteins");
        macroLabels.add("Fat");

        PieData pieData = new PieData(macroLabels, macroValueSet);
        pieData.setValueFormatter(new PercentFormatter());
        macroValueSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        macrosChart.setData(pieData);
        macrosChart.getLegend().setEnabled(false);
        macrosChart.setDrawHoleEnabled(false);
    }

    private void runModel(Bitmap bitMap) {
        FirebaseVisionImage visionImage = FirebaseVisionImage.fromBitmap(bitMap);
        FirebaseVisionCloudLabelDetector labelDetector = FirebaseVision.getInstance().getVisionCloudLabelDetector();
        getResults(labelDetector, visionImage);
    }

    private void getResults(FirebaseVisionCloudLabelDetector labelDetector, FirebaseVisionImage visionImage) {
        final TextView ans=findViewById(R.id.meal_image_lablel);
        Task<List<FirebaseVisionCloudLabel>> results = labelDetector.detectInImage(visionImage)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionCloudLabel>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionCloudLabel> labels) {
                        String labelAns="";
                        for (FirebaseVisionCloudLabel label : labels) {
                            Log.d("label", label.getLabel());
                            labelAns+=label.getLabel()+"\n";
                        }
                        ans.setText(labelAns);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("bug",e.toString());
                        ans.setText(e.getLocalizedMessage());
                    }
                });
    }
}
