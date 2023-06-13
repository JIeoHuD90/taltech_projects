#!/usr/bin/env python3
import sys

import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import ComplementNB
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier

from common import describe_data, test_env, classification_metrics


def read_data(file):
    """Return pandas dataFrame read from Excel file"""
    try:
        return pd.read_excel(file)
    except FileNotFoundError:
        sys.exit('ERROR: ' + file + ' not found')


def preprocess_data(df, verbose=False):
    y_column = 'In university after 4 semesters'

    # Features can be excluded by adding column name to list
    drop_columns = []

    categorical_columns = [
        'Faculty',
        'Paid tuition',
        'Study load',
        'Previous school level',
        'Previous school study language',
        'Recognition',
        'Study language',
        'Foreign student'
    ]
    for column in (categorical_columns):
        df[column] = df[column].fillna(value='Missing')

    # Handle dependent variable
    if verbose:
        print('Missing y values: ', df[y_column].isna().sum())

    y = df[y_column].values
    # Encode y. Naive solution
    y = np.where(y == 'No', 0, y)
    y = np.where(y == 'Yes', 1, y)
    y = y.astype(float)

    # Drop also dependent variable variable column to leave only features
    drop_columns.append(y_column)
    df = df.drop(labels=drop_columns, axis=1)

    # Remove drop columns for categorical columns just in case
    categorical_columns = [
        i for i in categorical_columns if i not in drop_columns]

    # STUDENT SHALL ENCODE CATEGORICAL FEATURES
    for column in (categorical_columns):
        df = pd.get_dummies(df, prefix=[column], columns=[column])

    # Handle missing data. At this point only exam points should be missing
    # It seems to be easier to fill whole data frame as only particular columns

    if verbose:
        describe_data.print_nan_counts(df)
    exams = ['Estonian language exam points', 'Estonian as second language exam points', 'Mother tongue exam points',
             'Narrow mathematics exam points', 'Wide mathematics exam points', 'Mathematics exam points']
    for column in exams:
        df[column] = df[column].fillna(value='0')

    if verbose:
        describe_data.print_nan_counts(df)

    # Return features data frame and dependent variable
    return df, y

# STUDENT SHALL CREATE FUNCTIONS FOR LOGISTIC REGRESSION CLASSIFIER, KNN
# CLASSIFIER, SVM CLASSIFIER, NAIVE BAYES CLASSIFIER, DECISION TREE
# CLASSIFIER AND RANDOM FOREST CLASSIFIER


def logistic_regression_classifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    clf = LogisticRegression(solver='newton-cg', random_state=0)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'Logistic')


def knn_clasifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    clf = KNeighborsClassifier(n_neighbors=7, p=2)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'KNN')


def svc_clasifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    clf = SVC(kernel='sigmoid', gamma=1, random_state=0, C=0.1)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'SVC')


def naive_clasifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    # Naive bayes does not need scaling, because it is not a distance based algorithm.
    # But if it is mandatory, then it can be done by sacrifising some accuracy
    #sc = StandardScaler()
    #X_train = sc.fit_transform(X_train)
    #X_test = sc.transform(X_test)
    #from sklearn.preprocessing import MinMaxScaler
    #clf = Pipeline([('Normalizing',MinMaxScaler()),('ComplementNB',ComplementNB())])
    clf = ComplementNB()
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'Naive')


def decision_tree_clasifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    clf = DecisionTreeClassifier(random_state=0)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'Tree')


def random_forest_clasifier(X, y):
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=0)
    sc = StandardScaler()
    X_train = sc.fit_transform(X_train)
    X_test = sc.transform(X_test)
    clf = RandomForestClassifier(n_estimators=15, random_state=0)
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    classification_metrics.print_metrics(y_test, y_pred, 'Forest')


if __name__ == '__main__':
    modules = ['numpy', 'pandas', 'sklearn']
    test_env.versions(modules)

    students = read_data('data/students.xlsx')
    students_X, students_y = preprocess_data(students, 0)
    drop_stud = students[(students['In university after 4 semesters'] == 'No')]
    describe_data.print_overview(
        drop_stud, file='results/dropped_overview.txt')
    describe_data.print_categorical(
        drop_stud, file='results/dropped_categorical_data.txt')
    describe_data.print_overview(
        students, file='results/students_overview.txt')
    describe_data.print_categorical(
        students, file='results/students_categorical_data.txt')

    logistic_regression_classifier(students_X, students_y)
    knn_clasifier(students_X, students_y)
    svc_clasifier(students_X, students_y)
    naive_clasifier(students_X, students_y)
    decision_tree_clasifier(students_X, students_y)
    random_forest_clasifier(students_X, students_y)

# STUDENT SHALL CALL CREATED CLASSIFIERS FUNCTIONS

print('Done')
