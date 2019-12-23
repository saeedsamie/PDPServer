package ML;

import IO.CSVHandler;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;

import java.util.ArrayList;

public class RandomTreeClassifier {
    int numLastItemClassify;
    ArrayList<RandomTree> attributeClassifierModel = new ArrayList<>();

    public RandomTreeClassifier(int numLastItemClassify) {
        this.numLastItemClassify = numLastItemClassify;
    }

    synchronized public void train(String fileName) {
//        final Evaluation eval;
        try {
            Instances trainingSet = new CSVHandler().read(fileName);
            if (trainingSet != null)
                for (int i = trainingSet.numAttributes() - numLastItemClassify; i < trainingSet.numAttributes(); i++) {
                    RandomTree classifierModel;
                    trainingSet.setClassIndex(i);
                    classifierModel = new RandomTree();
                    classifierModel.buildClassifier(trainingSet);
                    classifierModel.setKValue(0);
                    classifierModel.setMinNum(0);
                    Evaluation eval = new Evaluation(trainingSet);
                    eval.evaluateModel(classifierModel, trainingSet);
                    System.out.println(i + " " + eval.toSummaryString());
                    //                System.out.println(eval.toClassDetailsString());
                    //                System.out.println(eval.toCumulativeMarginDistributionString());
                    //                System.out.println(classifierModel.);
                    System.out.println("predict " + i + " att " + classifierModel.classifyInstance(trainingSet.instance(0)));
                    attributeClassifierModel.add(classifierModel);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public Instances predictDay(String fileName) {

        Instances predictingSet = null, predictedSet = null, readFromFile = null;
        try {
            readFromFile = new CSVHandler().read(fileName);
            predictingSet = readFromFile;
            predictedSet = readFromFile;
            if (predictingSet != null && attributeClassifierModel.size() > 0)
                for (int n = 0; n < predictingSet.numInstances(); n++) {
                    for (int i = predictingSet.numAttributes() - numLastItemClassify; i < predictingSet.numAttributes(); i++) {
                        try {
                            predictedSet.setClassIndex(i);
                            predictingSet.setClassIndex(i);
                            double label = attributeClassifierModel.get(i + numLastItemClassify - predictingSet.numAttributes()).classifyInstance(predictingSet.instance(n));
                            predictedSet.instance(n).setClassValue(label);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
//            System.out.println("DAY Pre Dic ting SET");
//            for (int i = 0; i < predictedSet.numInstances(); i++) {
//                for (int j = 0; j < predictedSet.numAttributes(); j++) {
//                    predictedSet.setClassIndex(j);
//                    System.out.print(predictedSet.instance(i).classValue() + " ");
//                }
//                System.out.print("\n");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictedSet;
    }

    synchronized public Instances predictTime(String fileName) {
        Instances predictingSet = null, predictedSet = null, readFromFile;
        try {
            readFromFile = new CSVHandler().read(fileName);
            predictingSet = readFromFile;
            predictedSet = readFromFile;
                if (readFromFile.numInstances() > 0 && attributeClassifierModel.size() > 47)

                    for (int n = 0; n < predictingSet.numInstances(); n++) {
                        for (int i = predictingSet.numAttributes() - numLastItemClassify; i < predictingSet.numAttributes(); i++) {
                            try {
                                predictedSet.setClassIndex(i);
                                predictingSet.setClassIndex(i);
                                double label = attributeClassifierModel.get(i + numLastItemClassify - predictingSet.numAttributes()).classifyInstance(predictingSet.instance(n));
                                predictedSet.instance(n).setClassValue(label);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

            System.out.println("TIME Pre Dic ting SET");
            for (int i = 0; i < predictedSet.numInstances(); i++) {
                for (int j = 0; j < predictedSet.numAttributes(); j++) {
                    predictedSet.setClassIndex(j);
                    System.out.print(predictedSet.instance(i).classValue() + " ");
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictedSet;
    }
}
