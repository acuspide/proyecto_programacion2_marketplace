#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

#define NUM_REGISTERS 8

// Configuración de la pantalla LCD (dirección y tamaño)
LiquidCrystal_I2C lcd(0x27, 16, 2); 

// Configuración del teclado numérico 4x4
const byte ROWS = 4; 
const byte COLS = 4; 
char keys[ROWS][COLS] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

byte rowPins[ROWS] = {9, 8, 7, 6}; // Pines conectados a las filas
byte colPins[COLS] = {5, 4, 3, 2}; // Pines conectados a las columnas

Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

// Banco de registros (8 registros de 8 bits)
byte registers[NUM_REGISTERS] = {0};

// Instrucciones (ISA) Opcodes
enum Opcodes {
  ADD = 0x0,
  AND = 0x1,
  OR = 0x2,
  NOT = 0x3,
  ADDI = 0x4,
  LDI = 0x5,
  LD = 0x6,
  ST = 0x7,
  JMP = 0x8,
  JZ = 0x9,
  JNZ = 0xA
};

// Flags de CPU
bool zeroFlag = false;

// Variables de control
int pc = 0; // Contador de programa

// Funciones para operaciones de ALU
void executeAdd(byte rd, byte rs1, byte rs2) {
  registers[rd] = registers[rs1] + registers[rs2];
  zeroFlag = (registers[rd] == 0);
}

void executeAnd(byte rd, byte rs1, byte rs2) {
  registers[rd] = registers[rs1] & registers[rs2];
  zeroFlag = (registers[rd] == 0);
}

void executeOr(byte rd, byte rs1, byte rs2) {
  registers[rd] = registers[rs1] | registers[rs2];
  zeroFlag = (registers[rd] == 0);
}

void executeNot(byte rd, byte rs) {
  registers[rd] = ~registers[rs];
  zeroFlag = (registers[rd] == 0);
}

// Cargar inmediato en registro
void executeLdi(byte rd, byte imm) {
  registers[rd] = imm;
}

// Función para leer entradas del pad numérico
void readKeypad() {
  char key = keypad.getKey();

  if (key) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Tecla presionada: ");
    lcd.setCursor(0, 1);
    lcd.print(key);

    // Lógica para seleccionar instrucciones
    switch (key) {
      case '1': executeAdd(0, 1, 2); break; // Ejemplo de instrucción ADD en R0 = R1 + R2
      case '2': executeAnd(0, 1, 2); break; // Ejemplo de instrucción AND
      case '3': executeOr(0, 1, 2); break;  // Ejemplo de instrucción OR
      case '4': executeNot(0, 1); break;    // Ejemplo de instrucción NOT
      case '5': executeLdi(0, 5); break;    // Ejemplo de LDI
      // Añadir más casos para diferentes instrucciones y operaciones según ISA
      case 'A': pc = 0; break;             // Reinicio del PC (Program Counter)
      case 'B': zeroFlag = false; break;   // Reinicio de zeroFlag
      case 'C': showRegisters(); break;    // Mostrar registros
      case 'D': showPCandFlags(); break;   // Mostrar PC y ZeroFlag
      default: break;
    }
  }
}

// Mostrar los registros en el LCD
void showRegisters() {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("R0:");
  lcd.print(registers[0]);
  lcd.print(" R1:");
  lcd.print(registers[1]);

  lcd.setCursor(0, 1);
  lcd.print("PC:");
  lcd.print(pc);
  lcd.print(" ZF:");
  lcd.print(zeroFlag);
}

// Mostrar el PC y los flags en el LCD
void showPCandFlags() {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("PC:");
  lcd.print(pc);
  lcd.setCursor(0, 1);
  lcd.print("Zero Flag:");
  lcd.print(zeroFlag);
}

// Configuración inicial
void setup() {
  lcd.backlight();
  lcd.print("Iniciando CPU");

  // Inicializar los registros a 0
  for (int i = 0; i < NUM_REGISTERS; i++) {
    registers[i] = 0;
  }

  delay(1000);
  lcd.clear();
}

// Bucle principal
void loop() {
  readKeypad(); // Leer entrada del teclado y ejecutar la instrucción correspondiente
  delay(500);   // Retardo para mejor legibilidad de la entrada
}
