package com.worldmer.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by Yagnik on 09-Aug-18.
 */

public class MathemeticalService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    MathService.Stub mBinder = new MathService.Stub() {
        @Override
        public int add(int val1, int val2) throws RemoteException {
            return val1 + val2;
        }

        @Override
        public int sub(int val1, int val2) throws RemoteException {
            return val1 - val2;
        }

        @Override
        public int div(int val1, int val2) throws RemoteException {
            return val1 / val2;
        }

        @Override
        public int mul(int val1, int val2) throws RemoteException {
            return val1 * val2;
        }

        @Override
        public int mod(int val1, int val2) throws RemoteException {
            return val1 % val2;
        }
    };
}
