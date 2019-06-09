package com.example.hsh.homesweethome;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.hsh.homesweethome.Models.Furniture;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class FurnitureARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private String GLB_ASSET;

    private static final String TAG = FurnitureARActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private Furniture furniture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            furniture = (Furniture) intent.getSerializableExtra("Furniture");
        }

        GLB_ASSET = furniture.getFurnitureModelUrl();


        setContentView(R.layout.ar_fragment);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        ModelRenderable.builder()
                .setSource(this, RenderableSource.builder().setSource(
                        this,
                        Uri.parse(GLB_ASSET),
                        RenderableSource.SourceType.GLB)
                        .setScale(0.01f)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(GLB_ASSET)
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load renderable " +
                                            GLB_ASSET, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (modelRenderable == null) {
                        return;
                    }

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the transformable andy and add it to the anchor.
                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                    andy.setParent(anchorNode);
                    andy.setRenderable(modelRenderable);
                    andy.select();
                });
    }

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}

