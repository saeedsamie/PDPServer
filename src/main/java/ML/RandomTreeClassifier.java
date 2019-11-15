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

    public void train(String fileName) {
//        final Evaluation eval;
        try {
            Instances trainingSet = CSVHandler.read(fileName);

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
//    public void train(String fileName) {
////        final Evaluation eval;
////
////        try {
////            Instances trainingSet = CSVHandler.read(fileName);
////            if (trainingSet.classIndex() == -1) {
////                trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
////            }
////            tree = new RandomTree();
////
////            tree.buildClassifier(trainingSet);
////            tree.setKValue(0);
////            tree.setMinNum(0);
////
////
////            // Test the model
////            eval = new Evaluation(trainingSet);
////            eval.evaluateModel(tree, trainingSet);
////
////
////            // Print the result à la Weka explorer:
//////            System.out.println(eval.toSummaryString());
//////            System.out.println(eval.toMatrixString());
//////            System.out.println(tree.toString());
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }

    //    public Instances predict(String fileName) {
//        Instances predictingSet = null;
//        try {
//            CSVLoader loader = new CSVLoader();
//            loader.setSource(new File("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\Presence_Detection_Prediction\\" + fileName));
//            predictingSet = loader.getDataSet();
//            for (int i = 0; i < predictingSet.numInstances(); i++) {
//                for (int n = K; n > 0; n--) {
//                    predictingSet.setClassIndex(predictingSet.numAttributes() - n);
//                    double label = tree.classifyInstance(predictingSet.instance(i));
//                    predictingSet.instance(i).setClassValue(label);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return predictingSet;
//    }
    public Instances predict(String fileName) {
        Instances predictingSet = null, predictedSet = null;

        try {
            predictingSet = CSVHandler.read(fileName);
            predictedSet = CSVHandler.read(fileName);
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

            System.out.println("Pre Dic ting SET");
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
