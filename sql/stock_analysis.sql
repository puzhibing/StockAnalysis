/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : stock_analysis

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-10-22 17:58:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` varchar(50) NOT NULL,
  `chName` varchar(20) DEFAULT NULL,
  `chShortName` varchar(10) DEFAULT NULL,
  `enName` varchar(50) DEFAULT NULL,
  `enShortName` varchar(20) DEFAULT NULL,
  `registerTime` date DEFAULT NULL COMMENT '注册日期',
  `url` varchar(50) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_companystock
-- ----------------------------
DROP TABLE IF EXISTS `t_companystock`;
CREATE TABLE `t_companystock` (
  `id` varchar(50) NOT NULL,
  `companyId` varchar(50) DEFAULT NULL,
  `stockCode` varchar(50) DEFAULT NULL,
  `stockTypeId` varchar(50) DEFAULT NULL,
  `listingTime` datetime DEFAULT NULL,
  `stockExchangeId` varchar(50) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_currentassets
-- ----------------------------
DROP TABLE IF EXISTS `t_currentassets`;
CREATE TABLE `t_currentassets` (
  `id` varchar(50) NOT NULL,
  `companyStockId` varchar(50) DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `moneyFunds` varchar(30) DEFAULT NULL,
  `transactionalFinancialAssets` varchar(30) DEFAULT NULL,
  `billReceivable` varchar(30) DEFAULT NULL,
  `accountsReceivable` varchar(30) DEFAULT NULL,
  `prepayments` varchar(30) DEFAULT NULL,
  `interestReceivable` varchar(30) DEFAULT NULL,
  `dividendReceivable` varchar(30) DEFAULT NULL,
  `otherReceivables` varchar(30) DEFAULT NULL,
  `stock` varchar(30) DEFAULT NULL,
  `nonCurrentAssetsDueWithinOneYear` varchar(30) DEFAULT NULL,
  `otherCurrentAssets` varchar(30) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_currentliabilities
-- ----------------------------
DROP TABLE IF EXISTS `t_currentliabilities`;
CREATE TABLE `t_currentliabilities` (
  `id` varchar(50) NOT NULL,
  `companyStockId` varchar(50) DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `shortTermLoan` varchar(30) DEFAULT NULL,
  `transactionalFinancialLiabilities` varchar(30) DEFAULT NULL,
  `billsPayable` varchar(30) DEFAULT NULL,
  `accountsPayable` varchar(30) DEFAULT NULL,
  `advancePayment` varchar(30) DEFAULT NULL,
  `payrollPayable` varchar(30) DEFAULT NULL,
  `taxesPayable` varchar(30) DEFAULT NULL,
  `interestPayable` varchar(30) DEFAULT NULL,
  `dividendPayable` varchar(30) DEFAULT NULL,
  `otherPayables` varchar(30) DEFAULT NULL,
  `nonCurrentLiabilitiesDueWithinOneYear` varchar(30) DEFAULT NULL,
  `otherCurrentLiabilities` varchar(30) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_noncurrentassets
-- ----------------------------
DROP TABLE IF EXISTS `t_noncurrentassets`;
CREATE TABLE `t_noncurrentassets` (
  `id` varchar(50) NOT NULL,
  `companyStockId` varchar(50) DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `availableForSaleFinancialAssets` varchar(30) DEFAULT NULL,
  `holdingAnExpiredInvestment` varchar(30) DEFAULT NULL,
  `longTermReceivables` varchar(30) DEFAULT NULL,
  `longTermEquityInvestment` varchar(30) DEFAULT NULL,
  `investmentRealEstate` varchar(30) DEFAULT NULL,
  `fixedAssets` varchar(30) DEFAULT NULL,
  `constructionInProgress` varchar(30) DEFAULT NULL,
  `engineerMaterial` varchar(30) DEFAULT NULL,
  `fixedAssetsCleanup` varchar(30) DEFAULT NULL,
  `productiveBiologicalAssets` varchar(30) DEFAULT NULL,
  `gasolineAssets` varchar(30) DEFAULT NULL,
  `intangibleAssets` varchar(30) DEFAULT NULL,
  `developmentExpenditure` varchar(30) DEFAULT NULL,
  `goodwill` varchar(30) DEFAULT NULL,
  `longTermPrepaidExpenses` varchar(30) DEFAULT NULL,
  `deferredTaxAssets` varchar(30) DEFAULT NULL,
  `otherNonCurrentAssets` varchar(30) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_noncurrentliabilities
-- ----------------------------
DROP TABLE IF EXISTS `t_noncurrentliabilities`;
CREATE TABLE `t_noncurrentliabilities` (
  `id` varchar(50) NOT NULL,
  `companyStockId` varchar(50) DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `longTermLoan` varchar(30) DEFAULT NULL,
  `bondsPayable` varchar(30) DEFAULT NULL,
  `longTermPayables` varchar(30) DEFAULT NULL,
  `specialPayable` varchar(30) DEFAULT NULL,
  `estimatedLiabilities` varchar(30) DEFAULT NULL,
  `deferredIncomeTaxLiabilities` varchar(30) DEFAULT NULL,
  `otherNonCurrentLiabilities` varchar(30) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stockexchange
-- ----------------------------
DROP TABLE IF EXISTS `t_stockexchange`;
CREATE TABLE `t_stockexchange` (
  `id` varchar(50) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stocktype
-- ----------------------------
DROP TABLE IF EXISTS `t_stocktype`;
CREATE TABLE `t_stocktype` (
  `id` varchar(50) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `del` varchar(2) DEFAULT NULL,
  `insertUserId` varchar(50) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
