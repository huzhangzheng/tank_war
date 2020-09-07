package com.kevin;

public abstract class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject o) {
        this.go = o;

    }
    
}
