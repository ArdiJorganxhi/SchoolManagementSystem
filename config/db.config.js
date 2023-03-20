module.exports = {
    HOST: "localhost",
    USER: "postgres",
    PASSWORD: "prizrenkosova04",
    DB: "schoolmanagementsystem",
    dialect: "postgres",
    pool: {
      max: 5,
      min: 0,
      acquire: 30000,
      idle: 10000
    }
  };