package View;

import ML.RandomTreeClassifier;
import weka.core.Instances;

public class TableView {
    RandomTreeClassifier timeClassifierModel;
    RandomTreeClassifier randomTreeDayClassifier;



    public TableView() {
        timeClassifierModel = new RandomTreeClassifier(48);
        timeClassifierModel.train("time_train.csv");
        randomTreeDayClassifier = new RandomTreeClassifier(1);
        randomTreeDayClassifier.train("day_train.csv");
    }

//    public NaiveBayesClassifier getTimeClassifierModel() {
//        return timeClassifierModel;
//    }
//
//    public RandomTreeClassifier getRandomTreeDayClassifier() {
//        return randomTreeDayClassifier;
//    }

    public Instances getTimeData() {
        return timeClassifierModel.predict("time_test.csv");
    }

    public Instances getDayData() {
        return randomTreeDayClassifier.predict("day_test.csv");
    }

}
