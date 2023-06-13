<?php

declare(strict_types=1);
error_reporting(-1);
const MILES_TO_KM = 1.60934;
$numOfDistances = rand(5, 20);
$arrDistances = [];
for ($x = 0; $x < $numOfDistances; $x++) {
  array_push($arrDistances, rand(1, 100));
}
echo "Unsorted";
print_r($arrDistances);
sort($arrDistances);
echo "Sorted";
print_r($arrDistances);
$arrMiles = [];
for ($x = 0; $x < count($arrDistances, COUNT_RECURSIVE); $x++) {
  $arrMiles[$arrDistances[$x]] = $arrDistances[$x] / MILES_TO_KM;
}
if (!array_key_exists($arrDistances[$x], $arrMiles)) {
}
echo "Miles";
print_r($arrMiles);
printf("KM\tMILES\n");
foreach ($arrMiles as $key => $var) {
  printf("%d\t%0.3f\n", $key, $var);
}
function getKey($testKey, &$arr)
{
  for ($x = 0; $x < count($arr, COUNT_RECURSIVE); $x++) {
    return (string)$testKey . (string)$alphabeth[$x];
  }
}
