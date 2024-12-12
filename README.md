# ESP32 & MicroPython IoT Water Dispenser

This project is a smart water dispenser system built using an ESP32 microcontroller, MicroPython, an ultrasonic sensor, and a relay. The system automatically dispenses water when an object is detected within 2 cm by the ultrasonic sensor, and stops dispensing when no object is detected.

## Features
- Uses an ultrasonic sensor to detect object proximity.
- Controls a water dispensing motor via a relay.
- Compact design suitable for IoT-based applications.

## Components
1. **ESP32**
2. **Ultrasonic Sensor (HC-SR04)**
3. **Relay Module**
4. **Motor and Power Source**
5. **Water Reservoir**

## Wiring Diagram
### Ultrasonic Sensor:
- `GND` -> `GND` on ESP32
- `VCC` -> `VIN` on ESP32
- `Echo` -> `D12` on ESP32
- `Trig` -> `D13` on ESP32

### Relay:
- `DC+` -> `VIN` on ESP32
- `DC-` -> `GND` on ESP32
- `IN` -> `D2` on ESP32
- Use `NO` (Normally Open) and `COM` (Common) pins on the relay to connect the motor and power source.

## Installation and Setup
1. Install MicroPython firmware on your ESP32.
2. Upload the MicroPython script to the ESP32 using tools like `ampy` or Thonny IDE.
3. Connect the components as described above.

## Usage
1. Power up the ESP32 and ensure the wiring is correct.
2. Place your hand or an object approximately 2 cm from the ultrasonic sensor to start water dispensing.
3. Move the object away to stop the water flow.

## Notes
- Ensure the power source for the motor is sufficient to drive it.
- Test the system thoroughly before integrating with a water reservoir.

## License
This project is open-source and available under the [MIT License](LICENSE).

---
Feel free to contribute or modify the system as needed for your specific requirements!
