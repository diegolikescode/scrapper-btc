#include <iostream>
#include "math.h"

int main() {
  int x, y;
  std::cin >> x >> y;

  int res = sum(x, y);
  std::cout << res << std::endl;
  return 0;
}
