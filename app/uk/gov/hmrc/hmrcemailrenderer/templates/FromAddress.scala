/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.hmrcemailrenderer.templates

import play.api.Play

case class FromAddress(f: Map[String, String] => String) {
  def apply(p: Map[String, String]) = f(p)
}

object FromAddress {
  import play.api.Play.current

  lazy val replyDomain = Play.configuration.getString("fromAddress.domain").
    getOrElse(throw new IllegalArgumentException("Missing config for key - fromAddress.domain"))

  def noReply(name: String): String = s"$name <noreply@$replyDomain>"

  lazy val govUkTeamAddress = noReply("Gov.uk Team")
}