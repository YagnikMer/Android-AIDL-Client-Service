// MathService.aidl
package com.worldmer.server;

// Declare any non-default types here with import statements

interface MathService {
    int add(int val1,int val2);
    int sub(int val1,int val2);
    int div(int val1,int val2);
    int mul(int val1,int val2);
    int mod(int val1,int val2);
}
