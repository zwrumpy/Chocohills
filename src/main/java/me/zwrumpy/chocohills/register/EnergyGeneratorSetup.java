package me.zwrumpy.chocohills.register;


import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.implementation.generator.AfkeiNator;
import me.zwrumpy.chocohills.machine.implementation.generator.RumpyNator;
import me.zwrumpy.chocohills.machine.implementation.generator.WaterNator;


public class EnergyGeneratorSetup {
    ChocoHills addon;

    public EnergyGeneratorSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        new RumpyNator();
        new WaterNator();
        new AfkeiNator();
    }
}
