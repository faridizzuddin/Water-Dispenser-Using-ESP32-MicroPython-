from machine import Pin, Timer
import time
import iotConnect

# Define pins
trig_pin = Pin(13, Pin.OUT)
echo_pin = Pin(12, Pin.IN)
relay_pin = Pin(2, Pin.OUT)

# Distance threshold (in cm)
distance_threshold = 6  # Adjust as needed

def get_distance():
    """Measures the distance using the ultrasonic sensor."""
    trig_pin.value(0)
    time.sleep_us(2)
    trig_pin.value(1)
    time.sleep_us(10)
    trig_pin.value(0)

    while echo_pin.value()  == 0:
        pulse_start = time.ticks_us()

    while echo_pin.value() == 1:
        pulse_end = time.ticks_us()

    pulse_duration = time.ticks_diff(pulse_end, pulse_start)
    distance = (pulse_duration * 0.0343) / 2  # Speed of sound = 343 m/s

    return distance

def dispense_water():
    """Turns on the water pump relay."""
    relay_pin.value(1)  # Activate relay (adjust logic if your relay is active low)
    print("Dispensing water...")

def stop_dispensing():
    """Turns off the water pump relay."""
    relay_pin.value(0)  # Deactivate relay
    print("Stopped dispensing water.")


while True:
    distance = get_distance()
    print("Distance:", distance, "cm")

    if distance <= distance_threshold:
        dispense_water()
    else:
        stop_dispensing()

    
    iotConnect.main()  # Call the iotConnect function to send data to the cloud
    
    time.sleep(0.2) # Adjust delay as needed.  Too short a delay might cause erratic behavior.