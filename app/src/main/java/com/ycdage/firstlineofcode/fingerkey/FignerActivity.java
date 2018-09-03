package com.ycdage.firstlineofcode.fingerkey;

import android.app.Activity;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.widget.Toast;

import com.ycdage.firstlineofcode.R;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FignerActivity extends Activity {

    private  KeyStore keyStore;
    private static final String DEFAULT_KEY_NAME = "default_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figner);

        if(supportFignerKey()){
            initKey();
            initChipher();
        }
    }

    private boolean supportFignerKey() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(this, "版本太低,滚", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            KeyguardManager keyguardManager = getSystemService(KeyguardManager.class);
            FingerprintManager fingerprintManager = getSystemService(FingerprintManager.class);
            if (fingerprintManager == null) {
                Toast.makeText(this, "版本太低,滚", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(this, "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void initChipher() {
        try {
            SecretKey key = (SecretKey) keyStore.getKey(DEFAULT_KEY_NAME, null);
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            showFingerPrintDialog(cipher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyGenerator.init(builder.build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFingerPrintDialog(Cipher cipher) {
        FingerprintDialogFragment fragment = new FingerprintDialogFragment();
        fragment.show(getFragmentManager(), "fingerprint");
    }

}
