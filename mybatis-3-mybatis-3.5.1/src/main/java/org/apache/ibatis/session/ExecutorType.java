/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.session;

/**
 * @author Clinton Begin
 */
public enum ExecutorType {
  // SimpleExecutor 是一种常规执行器，每次执行都会创建一个statement，用完后关闭。
  // ReuseExecutor 是可重用执行器，将statement存入map中，操作map中的statement而不会重复创建statement。
  // BatchExecutor 是批处理型执行器，doUpdate预处理存储过程或批处理操作，doQuery提交并执行过程。
  SIMPLE, REUSE, BATCH
}
