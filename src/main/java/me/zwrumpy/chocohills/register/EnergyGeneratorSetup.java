package me.zwrumpy.chocohills.register;


import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.area.AfkeiNator;
import me.zwrumpy.chocohills.machine.generator.RumpyNator;
import me.zwrumpy.chocohills.machine.generator.WaterNator;


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
