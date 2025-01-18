package com.example.demo;

import java.nio.ByteBuffer;

import com.zaxxer.nuprocess.NuAbstractProcessHandler;
import com.zaxxer.nuprocess.NuProcess;

class ProcessHandler extends NuAbstractProcessHandler {
    @Override
    public void onStart(NuProcess nuProcess) {
        System.out.println("Process started: " + nuProcess.getPID());
    }

    @Override
    public void onStdout(ByteBuffer buffer, boolean closed) {
        if (buffer != null) {
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.out.print(new String(bytes));
        }
    }

    @Override
    public void onStderr(ByteBuffer buffer, boolean closed) {
        if (buffer != null) {
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.err.print(new String(bytes));
        }
    }

    @Override
    public void onExit(int exitCode) {
        System.out.println("\nProcess exited with code: " + exitCode);
    }
}
