package View;

import ML.NaiveBayesClassifier;
import ML.RandomTreeClassifier;
import weka.core.Instances;

public class TableView {
    NaiveBayesClassifier timeClassifierModel;
    RandomTreeClassifier randomTreeDayClassifier;


    public TableView() {
        timeClassifierModel = new NaiveBayesClassifier();
        timeClassifierModel.train("time_train.csv");
        randomTreeDayClassifier = new RandomTreeClassifier();
        randomTreeDayClassifier.train("day_train.csv");
    }

    public NaiveBayesClassifier getTimeClassifierModel() {
        return timeClassifierModel;
    }

    public RandomTreeClassifier getRandomTreeDayClassifier() {
        return randomTreeDayClassifier;
    }

    public Instances getTimeData() {
        return timeClassifierModel.predict("time_test.csv", 25);
    }

    public Instances getDayData() {
        return randomTreeDayClassifier.predict("day_test.csv", 1);
    }

}
