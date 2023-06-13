#!/usr/bin/env python3
import sys


import numpy as np
import pandas as pd

import matplotlib.pyplot as plt

from sklearn.manifold import TSNE
from sklearn.cluster import KMeans
from sklearn.preprocessing import MinMaxScaler


from common import describe_data, test_env


def read_data(file):
    """Return pandas dataFrame read from Excel file"""
    try:
        return pd.read_csv(file)
    except FileNotFoundError:
        sys.exit('ERROR: ' + file + ' not found')


def plot_clusters(X, y, figure, file=''):
    colors = ['tab:blue', 'tab:orange', 'tab:green', 'tab:red', 'tab:purple',
              'tab:brown', 'tab:pink', 'tab:olive']
    markers = ['o', 'X', 's', 'D']
    color_idx = 0
    marker_idx = 0

    plt.figure(figure)

    for cluster in range(0, len(set(y))):
        plt.scatter(X[y == cluster, 0], X[y == cluster, 1],
                    s=5, c=colors[color_idx], marker=markers[marker_idx])
        color_idx = 0 if color_idx == (len(colors) - 1) else color_idx + 1
        marker_idx = 0 if marker_idx == (len(markers) - 1) else marker_idx + 1

    plt.title(figure)
    plt.xticks([])
    plt.yticks([])

    if file:
        plt.savefig(file, papertype='a4')

    plt.show()


def elbow(data):
    categorical_features = ['Country/Territory', 'Code', 'Year']
    for col in categorical_features:
        dummies = pd.get_dummies(data[col], prefix=col)
        data = pd.concat([data, dummies], axis=1)
        data.drop(col, axis=1, inplace=True)
    mms = MinMaxScaler()
    mms.fit(data)
    data_transformed = mms.transform(data)
    Sum_of_squared_distances = []
    K = range(1, 204)
    for k in K:
        km = KMeans(n_clusters=k)
        km = km.fit(data_transformed)
        Sum_of_squared_distances.append(km.inertia_)
    plt.plot(K, Sum_of_squared_distances, 'bx-')
    plt.xlabel('k')
    plt.ylabel('Sum_of_squared_distances')
    plt.title('Elbow Method For Optimal k')
    plt.savefig('results/cc_wcss_plot.png')


def visualize(df):

    categorical_features = ['Country/Territory', 'Code', 'Year']
    for column in (categorical_features):
        df = pd.get_dummies(df, prefix=[column], columns=[column])
    X = df.values
    n_clusters = 40
    k_means = KMeans(n_clusters=n_clusters, init='k-means++', random_state=42)
    y_kmeans = k_means.fit_predict(X)
    X_tsne = TSNE(n_components=2, random_state=0).fit_transform(X)
    plot_clusters(X_tsne, np.full(X_tsne.shape[0], 0), 'PCA visualisation without clusters',
                  'data/cc_tsne_no_clusters.png')
    plot_clusters(X_tsne, y_kmeans, 'PCA visualisation with clusters',
                  'data/cc_tsne_40_clusters.png')


if __name__ == '__main__':
    modules = ['numpy', 'pandas', 'sklearn', 'matplotlib']
    test_env.versions(modules)
    database = read_data('data/cause_of_deaths.csv')
    elbow(database)
    visualize(database)
    describe_data.print_overview(
        database, file='results/data_overview.txt')
print('Done')
