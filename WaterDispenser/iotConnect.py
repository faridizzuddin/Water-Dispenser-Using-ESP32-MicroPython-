import network
import time
from machine import Pin, time_pulse_us
from umqtt.simple import MQTTClient
import urequests

# MQTT Configuration
SERVER = "mqtt.favoriot.com"
CLIENT_ID = "umqtt_client"
USERNAME = "nEmXP677rmR1AgjQdQHh8xlyC1CJrNe8"
PASSWORD = "nEmXP677rmR1AgjQdQHh8xlyC1CJrNe8"
TOPIC = "nEmXP677rmR1AgjQdQHh8xlyC1CJrNe8/v2/streams"

# WiFi Configuration
SSID = "Phone Murah"
PASSWORD_WIFI = "ora diwenehi"

# HC-SR04 Pins
TRIG_PIN = 15  # GPIO15 (D15)
ECHO_PIN =  2 # GPIO4 (adjust as needed)

# Tank dimensions (in cm)
TANK_HEIGHT = 10  # Adjust this to match the height of your tank

# Setup GPIO
trig = Pin(TRIG_PIN, Pin.OUT)
echo = Pin(ECHO_PIN, Pin.IN)

# Function to measure distance using HC-SR04
def measure_distance():
    # Send a 10us pulse to trigger pin
    trig.value(0)
    time.sleep_us(2)
    trig.value(1)
    time.sleep_us(10)
    trig.value(0)
    
    # Measure the duration of the echo pulse
    duration = time_pulse_us(echo, 1, 30000)  # Timeout after 30ms
    if duration < 0:
        return -1  # Invalid measurement

    # Calculate distance in cm
    distance = (duration * 0.034) / 2
    return distance

# Function to calculate water level
def calculate_water_level():
    distance = measure_distance()
    if distance < 0 or distance > TANK_HEIGHT:
        return 0  # Assume empty tank for invalid readings
    return max(0, TANK_HEIGHT - distance)

# Connect to WiFi
def connect_wifi():
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.disconnect()
    if not wlan.isconnected():
        print('Connecting to network...')
        wlan.connect(SSID, PASSWORD_WIFI)
        while not wlan.isconnected():
            pass
    print('Network connected:', wlan.ifconfig())

# Send data to Favor IoT
def send_to_favoriot(water_level):
    client = MQTTClient(CLIENT_ID, SERVER, user=USERNAME, password=PASSWORD)
    client.connect()
    payload = '{"device_developer_id": "esp32@s65588", "data": {"water_level": ' + str(water_level) + '}}'
    client.publish(TOPIC, payload)
    client.disconnect()

# Telegram Bot Configuration
BOT_TOKEN = "7832908980:AAG9bSPmFQD-qboYDqKYIRA69fzWS1-BCig"  # Replace with your Bot Token
CHAT_ID = "1069593992"  # Replace with your Chat ID
TELEGRAM_URL = f"https://api.telegram.org/bot{BOT_TOKEN}/sendMessage"

# Function to send Telegram message
def send_telegram_message(water_level):
    message = f"Water Level Alert: The water level is {water_level} cm."
    payload = {
        "chat_id": CHAT_ID,
        "text": message
    }
    try:
        response = urequests.post(TELEGRAM_URL, json=payload)
        print("Telegram Notification Sent:", response.json())
    except Exception as e:
        print("Failed to send Telegram message:", e)

# Main loop
# Main loop
def main():
    connect_wifi()
    while True:
        water_level = calculate_water_level()
        print("Water Level:", water_level, "cm")
        send_to_favoriot(water_level)
        
        # Send Telegram notification if water level is below a threshold
        if water_level < 10:  # Replace '10' with your desired threshold
            send_telegram_message(water_level)
        
        time.sleep(2)  # Send data every 5 seconds



# Run the main program
main()
