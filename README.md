# insert-randomly

When you you want to spice up your data

## Usage

```clojure
(require '[insert-randomly.core :as r])
```

### `insert-randomly`

```clojure
core> (r/insert-randomly [1 2 3 4 5] :woops)
[1 2 3 :woops 4 5]
core> (r/insert-randomly [1 2 3 4 5] 3 :woops)
[1 2 :woops 3 4 :woops :woops 5]
```

### `insert-result-randomly`

```clojure
core> (r/insert-result-randomly [1 2 3 4 5] gensym)
[G__17452 1 2 3 4 5 G__17453 G__17454]
core> (r/insert-randomly [1 2 3 4 5] 3 (constantly :woops))
[1 2 :woops :woops 3 4 5 :woops]
```

### `replace-randomly`

```clojure
core> (r/replace-randomly [1 2 3 4 5] :ok)
[1 :ok 3 4 5]
core> (r/replace-randomly [1 2 3 4 5] 20 :ok)
[:ok :ok :ok :ok :ok]
;; also works with maps
core> (r/replace-randomly {:a 1 :b 2 :c 3} 2 :surprise)
{:a 1, :b :surprise, :c :surprise}
```

### `update-randomly`

```clojure
(require '[clojure.string :as str])
.core> (r/update-randomly ["the" "quick" "brown" "fox"] 2 str/reverse)
["the" "kciuq" "brown" "xof"]
;; again - we can use it with maps
core> (r/update-randomly {:the "the" :quick "quick" :brown "brown" :fox "fox"} 2 str/reverse)
{:the "the", :quick "quick", :brown "nworb", :fox "xof"}
```
