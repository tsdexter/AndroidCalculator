## AndroidCalculator

A homework assignment in COMP3025 was to create a "calculator" that can add 2 numbers together. I wanted to see if I could build the entire calculator functionality without googling the logic. This is my attempt at that.

In the interest of time and considering it didn't impede my goal I did look up how to display a decimal without `.0`, the answer (https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0) was originally committed here: https://github.com/tsdexter/AndroidCalculator/commit/6aa2435b37ece6ca7f6cc33b4e67e9537cbdfc73 and modified for `BigDecimal` support here: https://github.com/tsdexter/AndroidCalculator/commit/54b3f422a51e37966775a9fae972ae41f3439d31

# Todo:
- support order of operations (currently it just performs operations left to right, `×` and `÷` should take precedence)

# Demo
https://github.com/tsdexter/AndroidCalculator/blob/master/AndroidCalculatorDemo.mov 

