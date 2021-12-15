//package edu.csustan.cs3810.gacha_helper;
////Sarah Dueltgen
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.text.*;
//
//import java.util.*;
//import java.math.*;
//
//public class CompareBuild extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_compare);
//    }
//
//    //declare variables to hold artifact information for two artifacts
//
//    //first set is maximum distribution for each stat * 6 for amount of upgrades possible through leveling
//    float artifact1atk = (float) (6 * 19.45);
//    float artifact1atkPercent = (float) (6 * 5.83);
//    float artifact1critRate = (float) (6 * 3.89);
//    float artifact1critDmg = (float) (6 * 7.77);
//    float artifact1def = (float) (6 * 23.15);
//    float artifact1defPercent = (float) (6 * 7.29);
//    float artifact1elementalMastery = (float) (6 * 23.31);
//    float artifact1energyRecharge = (float) (6 * 6.48);
//    float artifact1hp = (float) (6 * 298.75);
//    float artifact1hpPercent = (float) (6 * 5.83);
//    float artifact1healingBonus = (float) 35.9;
//    float artifact1physicalDmg = (float) 58.3;
//    float artifact1elementalDmg = (float) 46.6;
//
//    //second set of stats is an example of sub-stat distribution from in-game
//    float artifact2atk;
//    float artifact2atkPercent = (float) (5.3);
//    float artifact2critRate;
//    float artifact2critDmg = (float) (21.8);
//    float artifact2def = (float) (21);
//    float artifact2defPercent;
//    float artifact2elementalMastery = (float) (65);
//    float artifact2energyRecharge;
//    float artifact2hp;
//    float artifact2hpPercent;
//    float artifact2healingBonus;
//    float artifact2physicalDmg;
//    float artifact2elementalDmg;
//
//    //gets string for artifact stat box which holds ideal stats for artifacts
//    public String getArt1() {
//
//        String resultStream = ("");
//
//        if (artifact2atk != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1atk);
//        if (artifact2atkPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1atkPercent) + "\n";
//        if (artifact2critRate != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1critRate) + "\n";
//        if (artifact2critDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1critDmg) + "\n";
//        if (artifact2def != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1def) + "\n";
//        if (artifact2defPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1defPercent) + "\n";
//        if (artifact2elementalMastery != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1elementalMastery) + "\n";
//        if (artifact2energyRecharge != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1energyRecharge) + "\n";
//        if (artifact2hp != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1hp) + "\n";
//        if (artifact2hpPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1hpPercent) + "\n";
//        if (artifact2healingBonus != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1healingBonus) + "\n";
//        if (artifact2physicalDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1physicalDmg) + "\n";
//        if (artifact2elementalDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact1elementalDmg) + "\n";
//        return resultStream;
//    }
//    String artifact1Stats = getArt1();
//
//    //gets string for artifact stat box which holds user artifact (example for now though)
//    public String getArt2() {
//
//        String resultStream = ("");
//
//        if (artifact2atk != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2atk);
//        if (artifact2atkPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2atkPercent) + "\n";
//        if (artifact2critRate != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2critRate) + "\n";
//        if (artifact2critDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2critDmg) + "\n";
//        if (artifact2def != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2def) + "\n";
//        if (artifact2defPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2defPercent) + "\n";
//        if (artifact2elementalMastery != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2elementalMastery) + "\n";
//        if (artifact2energyRecharge != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2energyRecharge) + "\n";
//        if (artifact2hp != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2hp) + "\n";
//        if (artifact2hpPercent != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2hpPercent) + "\n";
//        if (artifact2healingBonus != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2healingBonus) + "\n";
//        if (artifact2physicalDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2physicalDmg) + "\n";
//        if (artifact2elementalDmg != 0.0f)
//            resultStream = resultStream + Float.toString(artifact2elementalDmg) + "\n";
//        return resultStream;
//    }
//    String artifact2Stats = getArt2();
//
//
//    public void compareValuesButton() {
//        System.out.println();
//    }
//
//    public float compareValuesMath(float value1, float value2) {
//        float compare = (float) value1 - value2;
//        return compare;
//    }
//
//    //TextView YourArtifactStats = (TextView) findViewById(R.id.YourArtifactStats);
//    //artifact2Stats.setText();
//    //needs to print to "YourArtifactStats" box in activity_compare
//
//    //TextView PerfectArtifactStats = (TextView) findViewById(R.id.PerfectArtifactStats);
//    //artifact1Stats.setText();
//    //needs to print to "YourArtifactStats" box in activity_compare
//
//
//    //method to find value differences using compareValuesMath method above!
//    public void compareValues() {
//
//        float compareAtk = compareValuesMath(artifact1atk, artifact2atk);
//        float compareAtkPercent = compareValuesMath(artifact1atkPercent, artifact2atkPercent);
//        float compareCritRate = compareValuesMath(artifact1critRate, artifact2critRate);
//        float compareCritDmg = compareValuesMath(artifact1critDmg, artifact2critDmg);
//        float compareDef = compareValuesMath(artifact1def, artifact2def);
//        float compareDefPercent = compareValuesMath(artifact1defPercent, artifact2defPercent);
//        float compareElementalMastery = compareValuesMath(artifact1elementalMastery, artifact2elementalMastery);
//        float compareEnergyRecharge = compareValuesMath(artifact1energyRecharge, artifact2energyRecharge);
//        float compareHP = compareValuesMath(artifact1hp, artifact2hp);
//        float compareHPPercent = compareValuesMath(artifact1hpPercent, artifact2hpPercent);
//        float compareHealingBonus = compareValuesMath(artifact1healingBonus, artifact2healingBonus);
//        float comparePhysicalDmg = compareValuesMath(artifact1physicalDmg, artifact2physicalDmg);
//        float compareElementalDmg = compareValuesMath(artifact1elementalDmg, artifact2elementalDmg);
//    }
//    //to do in future: grab pre-existing artifact values from a list
//
//}