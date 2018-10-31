/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : stock_analysis

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-10-31 23:43:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cashflow
-- ----------------------------
DROP TABLE IF EXISTS `t_cashflow`;
CREATE TABLE `t_cashflow` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `crfsogas` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ort` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cortbar` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ciioa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cpfgas` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `poatot` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `poocrtba` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cffoa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncffoa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `crfir` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `crfii` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncidofaiaaoaitpp` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncrfdosaobu` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ocrtiahbr` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cioia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cofaiaaocpfpi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cpfi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncpbsaobu` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `poocrtia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cfooia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncffia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `crfi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `crfb` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ocrtfar` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ciofa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cfdr` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cpfdpoip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `poocrtfa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cfoofa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncfgbfra` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `np` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `aip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dofagadadopba` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `aoia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `aoltpe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `loiffaiaaolta` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `losofa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `lofvc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ll` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ditad` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `iiiditl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `lr` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `diori` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `iiopi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `other` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncffoac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dtc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `scbdwoy` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `flofa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cateotp` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `iboc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `eboce` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `iboce` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `caceatboty` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bocaceaeot` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `niicace` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `teoerfocace` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `chName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `chShortName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `enName` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `enShortName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `registerTime` date DEFAULT NULL,
  `url` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_companystock
-- ----------------------------
DROP TABLE IF EXISTS `t_companystock`;
CREATE TABLE `t_companystock` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `stockCode` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `stockTypeId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `listingTime` date DEFAULT NULL,
  `del` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `stockExchangeId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_currentassets
-- ----------------------------
DROP TABLE IF EXISTS `t_currentassets`;
CREATE TABLE `t_currentassets` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `moneyFunds` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tfa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `billReceivable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `accountsReceivable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `prepayments` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `interestReceivable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dividendReceivable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `otherReceivables` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `stock` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncadwoy` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `oca` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tca` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_currentliabilities
-- ----------------------------
DROP TABLE IF EXISTS `t_currentliabilities`;
CREATE TABLE `t_currentliabilities` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `stl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tfl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `billsPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `accountsPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `advancePayment` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `payrollPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `taxesPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `interestPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dividendPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `otherPayables` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `nldwoy` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ocl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tcl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_noncurrentassets
-- ----------------------------
DROP TABLE IF EXISTS `t_noncurrentassets`;
CREATE TABLE `t_noncurrentassets` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `afsfa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `haei` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ltr` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ltbi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ire` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fixedAssets` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cap` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `engineerMaterial` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pba` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `gasolineAssets` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `intangibleAssets` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `de` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `goodwill` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ltpe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dta` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `onca` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tnca` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_noncurrentliabilities
-- ----------------------------
DROP TABLE IF EXISTS `t_noncurrentliabilities`;
CREATE TABLE `t_noncurrentliabilities` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `ltl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bondsPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ltp` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `specialPayable` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `estimatedLiabilities` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ditl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dncl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tncl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_ownersequity
-- ----------------------------
DROP TABLE IF EXISTS `t_ownersequity`;
CREATE TABLE `t_ownersequity` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `pic` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `capitalReserve` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `lts` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `surplusReserve` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `undistributedProfit` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `toe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_ownersequitychange
-- ----------------------------
DROP TABLE IF EXISTS `t_ownersequitychange`;
CREATE TABLE `t_ownersequitychange` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `yebateoly` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `apc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `eec` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `batboty` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `aoioditcy` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `np` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `galdiioe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ncifvoafsfa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tiociooraioiuuem` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `itrtoei` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `other1` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `oiarc` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cibo` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `taospiitoe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `other2` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `pd` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `esr` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `dooos` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `other3` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `itooe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `csicocs` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ssticocs` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sstmufl` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `other4` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `bateoty` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_profit
-- ----------------------------
DROP TABLE IF EXISTS `t_profit`;
CREATE TABLE `t_profit` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `companyStockId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dataTime` date DEFAULT NULL,
  `businessIncome` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `operatingCost` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `btaa` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sellingExpenses` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `managementCost` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `financialCost` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ail` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fvci` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `adi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ifi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `iiojvajv` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `oii` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `operatingProfit` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `noi` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `noe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `paldoia` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `onoe` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `totalProfit` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ite` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `netProfit` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `eps` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `beps` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `deps` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_stockexchange
-- ----------------------------
DROP TABLE IF EXISTS `t_stockexchange`;
CREATE TABLE `t_stockexchange` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_stocktype
-- ----------------------------
DROP TABLE IF EXISTS `t_stocktype`;
CREATE TABLE `t_stocktype` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `del` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  `updateUserId` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
