package org.fyr.Sequencers;

public abstract class Sequencer {

    public abstract int nextId();
    public abstract int getCurrentId();
    public abstract void setCurrentId(int id);

}
