package com.worldmer.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by Yagnik on 09-Aug-18.
 */

public class CharacterService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    CharService.Stub mBinder = new CharService.Stub() {
        @Override
        public String getCharFromNumber(int num) throws RemoteException {
            String mSingleStrNum = null;
            String mCharNum = "";
            while (num > 0) {
                int rem = num % 10;
                num = num / 10;
                switch (rem) {
                    case 0:
                        mSingleStrNum = "ZERO";
                        break;
                    case 1:
                        mSingleStrNum = "ONE";
                        break;
                    case 2:
                        mSingleStrNum = "TWO";
                        break;
                    case 3:
                        mSingleStrNum = "THREE";
                        break;
                    case 4:
                        mSingleStrNum = "FOUR";
                        break;
                    case 5:
                        mSingleStrNum = "FIVE";
                        break;
                    case 6:
                        mSingleStrNum = "SIX";
                        break;
                    case 7:
                        mSingleStrNum = "SEVEN";
                        break;
                    case 8:
                        mSingleStrNum = "EIGHT";
                        break;
                    case 9:
                        mSingleStrNum = "NINE";
                        break;
                }
                mCharNum = mSingleStrNum + " " + mCharNum;
            }
            return mCharNum;
        }
    };
}
