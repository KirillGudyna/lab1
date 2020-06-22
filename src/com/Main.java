package com;

public class Main {
    public static void main(String[] args) {
        int Nb=11;
        double h;
        h = 1 / ((double)Nb - 1);
        double[] y=new double[Nb];
        double[] x=new double[Nb];
        double[] k=new double[Nb];
        double[] q=new double[Nb];
        double[] f=new double[Nb];
        double[] Fb=new double[Nb];
        double[] A=new double[Nb];
        double[] B=new double[Nb];
        double[] C=new double[Nb];
        double X0=0, q0=-1,X1=1,q1=Math.pow(Math.cos(1),2);
        for(int i=0;i<Nb;i++){
            x[i] = i * h;
            f[i] = x[i]*Math.sin(2*x[i]);
            k[i] = Math.pow(Math.cos(x[i]),2);
            q[i] = Math.sin(2*x[i]);
            C[i] = 2 * k[i] + h * h*q[i];
            Fb[i] = f[i] * h*h;
        }
        for (int i = 1; i < Nb-1; i++) {
            A[i] = k[i] - (k[i + 1] - k[i - 1]) / 4;
            B[i] = k[i] + (k[i + 1] - k[i - 1]) / 4;
        }
        A[0] = 0;
        B[0] = k[0] + k[1] / 4;
        A[10]= k[10] + k[9]/ 4;
        B[10] = 0;
        double[] alfa=new double[Nb];
        double[] beta=new double[Nb];
        double O1, W1, O2, W2, R, Q;

        R = h * X0 + h * h*q[0] / 2 + k[0] - (-1) * h * h*X0 / (2 * k[0]);
        Q = h * X1 + h * h*q[10] / 2 + k[10] - (-1) * h * h*X1 / (2 * k[10]);
        O1 = k[0] / R;
        W1 = (h*q0 + h * h*f[0] / 2 - h * h * (-1) * q0 / (2 * k[0])) / R;

        O2 = k[10] / Q;
        W2 = (h*q1 + h * h*f[10] / 2 + h * h * (-1)* q1 / (2 * k[10])) / Q;

        alfa[0] = O1;
        beta[0] = W1;
        for (int i = 1; i < Nb; i++)
        {
            alfa[i] = B[i] / (C[i] - alfa[i - 1] * A[i]);
            beta[i] = (Fb[i] + beta[i - 1] * A[i]) / (C[i] - alfa[i - 1] * A[i]);
        }
        y[10] = beta[10];
        for (int i = Nb - 2; i >= 0; i--)
        {
            y[i] = alfa[i] * y[i + 1] + beta[i];
        }
        System.out.println();
        for(int i=0;i<Nb;i++){
            //System.out.println("u("+x[i]+")="+y[i]);
            System.out.printf("u(%.1f)=%.5f\n",x[i],y[i]);
        }
        //System.out.println("UU");
    }
}
